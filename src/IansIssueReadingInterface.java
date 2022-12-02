//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import javax.swing.*;

/**
 * The User interface class that houses the Issue reading application window.
 * Contains all properties for the Frame. The methods that perform tasks on the User interface, window listener methods,
 * Action Listener methods for the buttons.
 */
public class IansIssueReadingInterface extends JFrame implements ActionListener, WindowListener {
    String currentIssue;
    String[] FileList = new String[]{"Ians_W07_SO2_202604061100.csv", "Ians_W07_NO2_202710201500.csv",
            "Ians_W07_CO_202604061100.csv", "Ians_W07_OO_202604061100.csv"};
    String[] issueTypes = new String[]{"SO2", "NO2", "CO", "OO"};
    String[] exportTypes = new String[]{"RAF","DAT","RPT", "ALL"};
    HashMap<String, ReadingData> iansDataHash = new HashMap<String, ReadingData>();
    int gridStartingPosX = 160;
    int gridStartingPosY = 100;
    Color defaultLabelColor = new Color(0,0,0,20);
    Dimension defaultLabelDim = new Dimension(100, 30);

    SpringLayout myLayout = new SpringLayout();
    public FileManager file = new FileManager();

    GridPiece[][] gridArray;
    DrawPanel backgroundGrid;
    JButton btnSulphur, btnCarbonM, btnNitrous, btnObstructions,btnClose, btnExport, btnLoadFile;
    JLabel lblReadingValue, lblWarehouse, lblDate, lblTime, lblDangerous, lblConcerning;
    JLabel lblAcceptable, lblIssueType, lblLocation, lblIcon;
    ImageIcon imageIcon;

    public JTextField txtGridVal;

    /**
     * Constructor for the Issue Reading Window
     * gives all properties to the frame and Initiates the startup procedures for the window.
     */
    public IansIssueReadingInterface() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.buildDataStructures();
        this.backgroundGrid = new DrawPanel();
        this.setSize(200 + (iansDataHash.get(currentIssue).getData().get(0).size() * 20),
                150 + iansDataHash.get(currentIssue).getData().size() *20);
        this.setLayout(this.myLayout);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(this);
        this.setResizable(false);
        this.placeTextBoxes();
        this.placeLabels();
        this.placeButtons();
        this.refreshDynamicElements();
        this.add(this.backgroundGrid);
        this.setVisible(true);
    }

    /**
     * places all labels that display text information on the window.
     * places the dynamic labels that change when actions are performed.
     * places the image icon on the screen for Ian's Industrial.
     */
    public void placeLabels() {


        lblReadingValue = UIComponentLibrary.createALabel("Reading Value: ", -100, 0,
                this,myLayout, txtGridVal);
        lblReadingValue.setBackground(defaultLabelColor);
        lblReadingValue.setPreferredSize(defaultLabelDim);
        lblReadingValue.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        lblReadingValue.setOpaque(true);

        lblDangerous = UIComponentLibrary.createDangerLabel("Dangerous", 3, 280, this, myLayout);
        lblDangerous.setBackground(Color.RED);
        lblConcerning = UIComponentLibrary.createDangerLabel("Concerning", 3, 245, this, myLayout);
        lblConcerning.setBackground(Color.YELLOW);
        lblAcceptable = UIComponentLibrary.createDangerLabel("Acceptable", 3, 210, this, myLayout);
        lblAcceptable.setBackground(Color.GREEN);
        lblConcerning = UIComponentLibrary.createDangerLabel("Negligible", 3, 175, this, myLayout);
        lblConcerning.setBackground(Color.WHITE);

        buildReadingLabels();

        try{
            imageIcon = new ImageIcon(getClass().getResource("iconIans.png"));
            lblIcon = new JLabel(imageIcon);
            this.add(lblIcon);
            myLayout.putConstraint(SpringLayout.WEST, lblIcon, 20 ,SpringLayout.WEST, this);
            myLayout.putConstraint(SpringLayout.NORTH, lblIcon, 20, SpringLayout.NORTH, this);
        }
        catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    /**
     * Removes and replaces the dynamic reading detail labels that change when a new Issue is selected.
     * This makes the reading labels Dynamic to the current reading type
     */
    private void buildReadingLabels() {
        removeReadingLables();

        lblWarehouse = UIComponentLibrary.createALabel(iansDataHash.get(currentIssue).getWarehouseName(),
                160,5, this, myLayout);
        lblWarehouse.setOpaque(true);
        lblWarehouse.setBackground(defaultLabelColor);
        lblWarehouse.setPreferredSize(defaultLabelDim);
        lblWarehouse.setHorizontalAlignment(SwingConstants.CENTER);

        lblDate = UIComponentLibrary.createALabel(iansDataHash.get(currentIssue).getDate(),
                0,30,this, myLayout,lblWarehouse);
        lblTime = UIComponentLibrary.createALabel(iansDataHash.get(currentIssue).getTime(),
                100, 0,this,myLayout,lblDate);
        lblLocation = UIComponentLibrary.createALabel(iansDataHash.get(currentIssue).getLocation(),
                100,0,this,myLayout,lblWarehouse);
        lblIssueType = UIComponentLibrary.createALabel(iansDataHash.get(currentIssue).getIssueType(),
                0, 30,this, myLayout, lblDate);
        lblIssueType.setPreferredSize(new Dimension(200,30));
    }

    /**
     * removes the reading labels so the application can replace them with new ones.
     */
    private void removeReadingLables() {
        if (lblWarehouse == null){
            return;
        }
        this.remove(lblWarehouse);
        this.remove(lblDate);
        this.remove(lblTime);
        this.remove(lblIssueType);
        this.remove(lblLocation);
    }

    /**
     * places all buttons that application uses to perform tasks
     */
    public void placeButtons() {
        int defaultBtnLen = 25;
        int defaultBtnWidth = 150;
        int defaultIssueBtnPosX = 3;
        int defaultIssueBtnPosY = 355;

        btnSulphur = UIComponentLibrary.createAButton("Sulphur Dioxide", defaultBtnWidth, defaultBtnLen,
                defaultIssueBtnPosX, defaultIssueBtnPosY, this, this, myLayout);
        btnNitrous = UIComponentLibrary.createAButton("Nitrogen Dioxide", defaultBtnWidth, defaultBtnLen,
                0, defaultBtnLen, this, this, myLayout, btnSulphur);
        btnCarbonM = UIComponentLibrary.createAButton("Carbon Monoxide", defaultBtnWidth, defaultBtnLen,
                0, defaultBtnLen, this, this, myLayout, btnNitrous);
        btnObstructions = UIComponentLibrary.createAButton("Obstruction", defaultBtnWidth, defaultBtnLen,
                0, defaultBtnLen, this, this, myLayout, btnCarbonM);

        btnLoadFile = UIComponentLibrary.createAButton("Load File", defaultBtnWidth, defaultBtnLen,
                410, 70,this,this,myLayout);
        btnExport = UIComponentLibrary.createAButton("Export", defaultBtnWidth, defaultBtnLen,
                410, 40,this,this,myLayout);
        btnClose = UIComponentLibrary.createAButton("Close", defaultBtnWidth, defaultBtnLen,
                410, 10,this,this,myLayout);
    }

    /**
     * places the Grid values text box that will display the reading value for each grid square
     */
    public void placeTextBoxes() {
        txtGridVal = UIComponentLibrary.createATextField(2, 104, 320, this, this.myLayout);
        txtGridVal.setFont(new Font("Verdana", 1, 20));
        txtGridVal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txtGridVal.setEditable(false);
    }

    /**
     * This will method will remove all elements from the paage and replace them with update elements that reflect
     * new Issue type that was selected
     * Resizes the window to fit the new grid size place the button elements
     */
    public void refreshDynamicElements() {
        buildGrid();
        colourGrid();
        buildReadingLabels();

        this.setSize(190 + (iansDataHash.get(currentIssue).getData().get(0).size() * 20),
                150 + iansDataHash.get(currentIssue).getData().size() *20);
        this.invalidate();
        this.validate();
        this.repaint();
    }

    /**
     * Removes the grid squares/ pieces from the frame then replaced them with the currently selected Issue type.
     */
    private void refreshGrid() {
        for(int i = 0; i < gridArray.length; ++i) {
            for(int j = 0; j < gridArray[0].length; ++j) {
                this.remove(gridArray[i][j]);
            }
        }
        refreshDynamicElements();
    }

    /**
     * Builds the Hashmap that houses the Reading data objects
     */
    public void buildDataStructures() {
        for(int i = 0; i < FileList.length; ++i) {
            ReadingData readingData = file.readFile(FileList[i]);
            iansDataHash.put(issueTypes[i], readingData);
        }
        currentIssue = "NO2";
    }

    /**
     * Builds the grid square/ piece array and add each grid piece to the frame
     *
     */
    private void buildGrid() {

        int gridGap = 20;
        gridArray = new GridPiece[iansDataHash.get(currentIssue).getData().size()]
                [iansDataHash.get(currentIssue).getData().get(0).size()];

        for(int y = 0; y < gridArray.length; ++y) {
            for(int x = 0; x < gridArray[y].length; ++x) {
                GridPiece myGridPiece = UIComponentLibrary.createGridPiece(19, this.gridStartingPosX
                        + x * gridGap, this.gridStartingPosY + y * gridGap, this, this.myLayout);
                myGridPiece.setIssueVal(iansDataHash.get(currentIssue).getData().get(y).get(x));
                myGridPiece.setDisplayTextBox(txtGridVal);
                gridArray[y][x] = myGridPiece;

            }
        }
    }

    /**
     * Determines the current issue type and applies the colouring logic to the grid square
     */
    public void colourGrid() {

                switch(currentIssue) {
                    case "CO":
                        if (currentIssue.equalsIgnoreCase("CO")) {
                            for (int i = 0; i < gridArray.length; ++i) {
                                for (int j = 0; j < (gridArray[i]).length; ++j) {
                                    if (gridArray[i][j].getIssueVal() <= 1) {
                                        gridArray[i][j].setBackground(Color.WHITE);
                                    } else if (gridArray[i][j].getIssueVal() <= 9) {
                                        gridArray[i][j].setBackground(Color.GREEN);
                                    } else if (gridArray[i][j].getIssueVal() <= 19) {
                                        gridArray[i][j].setBackground(Color.YELLOW);
                                    } else {
                                        gridArray[i][j].setBackground(Color.RED);
                                    }
                                }
                            }
                        }
                    case "NO2":
                        if (currentIssue.equalsIgnoreCase("NO2")) {
                            for (int i = 0; i < gridArray.length; ++i) {
                                for (int j = 0; j < (gridArray[i]).length; ++j) {
                                    if (gridArray[i][j].getIssueVal() <= 1) {
                                        gridArray[i][j].setBackground(Color.WHITE);
                                    } else if (gridArray[i][j].getIssueVal() <= 8) {
                                        gridArray[i][j].setBackground(Color.GREEN);
                                    } else if (gridArray[i][j].getIssueVal() <= 18) {
                                        gridArray[i][j].setBackground(Color.YELLOW);
                                    } else {
                                        gridArray[i][j].setBackground(Color.RED);
                                    }
                                }
                            }
                        }
                    case "SO2":
                        if (currentIssue.equalsIgnoreCase("SO2")) {
                            for (int i = 0; i < gridArray.length; ++i) {
                                for (int j = 0; j < (gridArray[i]).length; ++j) {
                                    if (gridArray[i][j].getIssueVal() <= 3) {
                                        gridArray[i][j].setBackground(Color.WHITE);
                                    } else if (gridArray[i][j].getIssueVal() <= 11) {
                                        gridArray[i][j].setBackground(Color.GREEN);
                                    } else if (gridArray[i][j].getIssueVal() <= 21) {
                                        gridArray[i][j].setBackground(Color.YELLOW);
                                    } else {
                                        gridArray[i][j].setBackground(Color.RED);
                                    }
                                }
                            }
                        }
                    case "OO":
                        if (currentIssue.equalsIgnoreCase("OO")) {
                            for (int i = 0; i < gridArray.length; ++i) {
                                for (int j = 0; j < (gridArray[i]).length; ++j) {
                                    if (gridArray[i][j].getIssueVal() <= 0) {
                                        gridArray[i][j].setBackground(Color.WHITE);
                                    } else if (gridArray[i][j].getIssueVal() <= 1) {
                                        gridArray[i][j].setBackground(Color.GREEN);
                                    } else if (gridArray[i][j].getIssueVal() <= 2) {
                                        this.gridArray[i][j].setBackground(Color.YELLOW);
                                    } else {
                                        this.gridArray[i][j].setBackground(Color.RED);
                                    }
                                }
                            }
                        }
                }
        iansDataHash.get(currentIssue).setGridArray(gridArray);
    }


    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    /**
     * The action listener that will listen for actions performed and perform a task depending on what happens
     * @param e the ui component interacted with.
     */
    public void actionPerformed(ActionEvent e) {


//      when pressed the grid and reading labels will change to reflect the new reading data
        if (e.getSource() == this.btnSulphur) {
            this.currentIssue = this.issueTypes[0];
            refreshGrid();
        }
        //      when pressed the grid and reading labels will change to reflect the new reading data

        if (e.getSource() == btnNitrous) {
            currentIssue = issueTypes[1];
            refreshGrid();
        }
        //      when pressed the grid and reading labels will change to reflect the new reading data

        if (e.getSource() == btnCarbonM) {
            String pastReading = currentIssue;
            currentIssue = issueTypes[2];
            refreshGrid();
        }
        //      when pressed the grid and reading labels will change to reflect the new reading data

        if (e.getSource() == btnObstructions) {
            currentIssue = issueTypes[3];
            refreshGrid();
        }
        // Opens a file selector, loads the filename and file path checks the file name for what type of file it is
        // reads the file in and replaces the reading data of the same type.
        if (e.getSource() == btnLoadFile){
            String loadFileName = file.chooseFile();
            boolean loadSuccessFull = false;
            for (String issue :issueTypes
                 ) {
                if (loadFileName.contains(issue)) {
                    currentIssue = issue;
                    iansDataHash.remove(issue);
                    iansDataHash.put(issue,file.readFile(loadFileName));
                    refreshGrid();
                    if (iansDataHash.get(currentIssue).getData() != null){
                        loadSuccessFull = true;
                    }
                    break;
                }
            }
            if(!loadSuccessFull || iansDataHash.get(currentIssue).getData() == null) {
                JOptionPane.showMessageDialog(null, "File name missing Issue Identifier");
                buildDataStructures();
            }
        }
        // opens an option window when depending on the choice a different file will be exported.
        if (e.getSource() == btnExport){
            int exportType = JOptionPane.showOptionDialog(null,
                    "Which type of file would your like to export?",
                    "Export", JOptionPane.NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null, exportTypes,"default");
            if (exportType == 0) {
                file.saveDialog(iansDataHash.get(currentIssue),
                        exportTypes[exportType]);
            }
            else if (exportType == 1)
            {
                file.saveDialog(iansDataHash.get(currentIssue),
                        exportTypes[exportType]);
            }
            else if (exportType == 2)
            {
                file.saveDialog(iansDataHash.get(currentIssue),
                        exportTypes[exportType]);
            }
            else if (exportType == 3 )
            {
                for (String export: exportTypes ) {
                    if (export == "ALL")
                    {
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Exporting " + export + "File type.");
                    file.saveDialog(iansDataHash.get(currentIssue),
                            export);
                }
            }
        }
        // closes the application
        if (e.getSource() == btnClose) {
            System.exit(0);
        }

    }


}
