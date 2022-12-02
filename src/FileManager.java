import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import javax.swing.*;

public class FileManager extends JFrame {

    private String line;
    ArrayList<ArrayList> dataArrayList;
    private JFileChooser chooser = new JFileChooser();

    /**
     * Selects a file to be loaded into the application
     * must contain CO, SO2, NO2, or OO in the file name or the file won't read.
     * @return
     */
    public String chooseFile() {
        chooser.setCurrentDirectory(new File("."));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = new File(chooser.getSelectedFile().getAbsolutePath());
            return selectedFile.toString();
        }
        else {
            JOptionPane.showMessageDialog(null, "No option selected.\nUsing NO2 as default file.");
            return "Ians_W07_NO2_202710201500.csv";
        }
    }

    /**
     * Reads a file nome and returns the reading data object created from the file
     * Files must contain CO, SO2, NO2 or OO or else the reading will fail.
     * @param fileName
     * @return
     */
    public ReadingData readFile(String fileName){
        try {
            FileInputStream fileStream = new FileInputStream(fileName);
            DataInputStream input = new DataInputStream(fileStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(input));
            ReadingData readingData = new ReadingData();
            ArrayList<ArrayList<Integer>> dataArrayList= new ArrayList<>(1);

            int lineCount = 0;
            if (fileName.contains("SO2")) {
                readingData.setIssueType("Sulphur Dioxide - SO2");
            }
            else if (fileName.contains("NO2")) {
                readingData.setIssueType("Nitrogen Dioxide - NO2");
            }
            else if (fileName.contains("CO")) {
                readingData.setIssueType("Carbon Monoxide - CO");
            }
            else if (fileName.contains("OO")) {
                readingData.setIssueType("Obstruction");
            }
            else {
                throw new Exception("File name missing Issue Identifier");
            }

            while ((line = bReader.readLine()) != null && !line.isEmpty() && !line.trim().isEmpty()) {
                ArrayList<String> splitLines = new ArrayList<String>(1);

                if (lineCount == 0){
                    Collections.addAll(splitLines,line.replace(",","").split("-"));
                    readingData.setWarehouseName(splitLines.get(0).trim());
                    readingData.setLocation(splitLines.get(1).trim());
                }
                else if (lineCount == 1){
                    readingData.setDate(line.replace(",",""));
                }
                else if (lineCount == 2){
                    readingData.setTime(line.replace(",",""));
                }
                else {
                    Collections.addAll(splitLines, line.split(","));
                    ArrayList<Integer> temp = new ArrayList<>(1);
                    for (String num : splitLines) {
                        temp.add(Integer.parseInt(num));
                    }

                    if (temp.size() > 1) {
                        dataArrayList.add(temp);
                    }
                    else {
                        throw new Exception("Invalid file format");
                    }
                }
            lineCount++;
            }
            readingData.setData(dataArrayList);
            System.out.println("Data loaded successfully,");
            System.out.println(readingData.toString());
            return readingData;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

            return null;
        }
    }


    /**
     * chooses the files save directory, Runs the appropriate save function.
     * @param readingData
     * @param filetype
     */
    public void saveDialog(ReadingData readingData, String filetype) {
        FileDialog fileDialog = new FileDialog(this,"Choose where to save?", FileDialog.SAVE);
        chooser.setCurrentDirectory(new File("."));
        fileDialog.setDirectory(chooser.getCurrentDirectory().getAbsolutePath());
        fileDialog.setFile(String.format("*.%s",filetype));
        fileDialog.setVisible(true);

        String filename = fileDialog.getDirectory() + fileDialog.getFile();

        if (filetype.equals("RPT")) {
            if (!filename.endsWith(".RPT")) {
                filename += ".RPT";
                exportRPT(filename, readingData);
            }
            exportRPT(filename,readingData);
        }
        else if (filetype.equals("RAF")) {
            if (!filename.endsWith(".RAF")) {
                filename += ".RAF";
            }
            exportRaf(filename, readingData);
        }
        else if (filetype.equals("DAT")) {
                if (!filename.endsWith(".DAT")) {
                    filename += ".DAT";
                }
            exportDat(filename, readingData);
        }
        else {
            System.out.println("Error on setting file");
        }
    }

    /**
     * Reads in a file name and a reading Data object and exports an RPT file
     * the file contains the number of times that colour appears consecutively in a row and the color name.
     * @param filename the name of the file to be writen too
     * @param readingData the reading data object that contains the grid squares/pieces that are checked.
     */
    private void exportRPT(String filename, ReadingData readingData) {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            for (int i = 0; i < readingData.getData().size(); i++) {
                int count = 0;
                Color colour = readingData.getGridArray()[i][0].getBackground();
                Color currentColor = colour;

                for (int j = 0; j < readingData.getData().get(i).size(); j++) {
                    currentColor = readingData.getGridArray()[i][j].getBackground();
                    if (colour != currentColor) {
                        writer.print(count +" "+ findColorAsString(currentColor) + ", ");
                        colour = currentColor;
                        count = 1;
                    } else {
                        count++;
                    }

                }
                writer.print(count +" "+ findColorAsString(currentColor));
                colour = currentColor;
                writer.println("");

            }
            writer.close();
        }

        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * used in the RPT method to translate Color objects into String Text.
     * @param colour the color to determn the name of the colour.
     * @return String text of the colour name.
     */
    private String findColorAsString(Color colour) {
        if (colour == Color.RED)
        {
            return "Red";
        } else if (colour == Color.YELLOW) {
            return "Yellow";
        } else if (colour == Color.GREEN) {
            return "Green";
        } else {
            return "White";
        }

    }

    /**
     * takes in the file name and the reading data to export a DAT file that is formatted
     * with the coordinates of the reading value and the reading value on each line.
     *
     * @param filename
     * @param readingData
     */
    private void exportDat(String filename, ReadingData readingData) {

        try {
            PrintWriter datWriter = new PrintWriter(new FileWriter(filename));
            for (int i = 0; i < readingData.getData().size(); i++) {
                for (int j = 0; j < readingData.getData().get(i).size(); j++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(i + ", ");
                    stringBuilder.append(j + ", ");
                    if (readingData.getGridArray()[i][j].getBackground() == Color.RED) {
                        stringBuilder.append("R");
                    }
                    else if(readingData.getGridArray()[i][j].getBackground() == Color.YELLOW) {
                        stringBuilder.append("Y");
                    }
                    else if (readingData.getGridArray()[i][j].getBackground() == Color.GREEN) {
                        stringBuilder.append("G");
                    }
                    else {
                        stringBuilder.append("W");
                    }
                    datWriter.println(stringBuilder);
                }
            }
            datWriter.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error Exporting Dat fIle.\n" + e);
        }
    }

    /**
     * Saves reading data that is currently selected as an RAF file
     *
     * @param fileName
     * @param readingData
     */
    public void exportRaf(String fileName, ReadingData readingData) {
        try {
            RandomAccessFile EXFile = new RandomAccessFile(fileName ,"rw");
            StringBuilder stringBuilder = new StringBuilder();


            EXFile.seek(0);
            EXFile.writeUTF(readingData.getWarehouseName() + " - " + readingData.getLocation());
            EXFile.seek(100);
            EXFile.writeUTF(readingData.getDate());
            EXFile.seek(200);
            EXFile.writeUTF(readingData.getTime());
            EXFile.seek(300);

            int rafIncrement = 3;
            for (int i = 0; i < readingData.getData().size(); i++) {
                for (int j = 0; j < readingData.getData().get(i).size(); j++) {
                    if (j == readingData.getData().get(i).size()-1) {
                        int pointer = rafIncrement * 100;
                        EXFile.seek(pointer);
                        EXFile.writeInt(i);
                        rafIncrement++;
                    }
                    else {
                        int pointer = rafIncrement * 100;
                        EXFile.seek(pointer);
                        EXFile.writeInt(i);
                        EXFile.writeUTF(",");
                        rafIncrement++;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
