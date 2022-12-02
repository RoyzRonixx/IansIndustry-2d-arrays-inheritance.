import java.util.ArrayList;

/**
 * The reading data object that stores the data after being read into the file.
 */
    public class ReadingData {
        private String warehouseName;
        private String location;
        private String date;
        private String time;
        private ArrayList<ArrayList<Integer>> data;
        private GridPiece[][] gridArray;
        private String issueType;

    /**
     * Blank reading data constructor.
     */
        ReadingData() {
        }

    /**\
     * gets the data from the object
     * @return data as int 2d array
     */
        public ArrayList<ArrayList<Integer>> getData() {
            return this.data;
        }

    /**
     * saves the data to the object
     * @param data Data as String
     */
        public void setData(ArrayList<ArrayList<Integer>> data) {
            this.data = data;
        }

    /**
     * pulls the time from the object
     * @return Time as string
     */
        public String getTime() {
            return this.time;
        }

    /**
     * saves the time to the object
     * @param time time as string
     */
        public void setTime(String time) {
            this.time = time;
        }

    /**
     * pulls the date from the object
     * @return data as string
     */
        public String getDate() {
            return this.date;
        }

    /**
     * save the date to the object
     * @param date date as string
     */
        public void setDate(String date) {
            this.date = date;
        }

    /**
     * pulls the warehouse name from the object
     * @return the warehouse name as String
     */
        public String getWarehouseName() {
            return this.warehouseName;
        }

    /**
     * save the warehouse name to the object
     * @param warehouseName warehouse name as string
     */
        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

    /**
     * pulls the location name from the object
     * @return location as string
     */
        public String getLocation() {
            return this.location;
        }

    /**
     * save the location to the object
     * @param location location name as string
     */
        public void setLocation(String location) {
            this.location = location;
        }

    /**
     * prints the reading data to console for testing purposes
     * @return the details of the reading data as a string
     */
        public String toString() {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(this.warehouseName);
            sBuilder.append("\n");
            sBuilder.append(this.location);
            sBuilder.append("\n");
            sBuilder.append(this.date);
            sBuilder.append("\n");
            sBuilder.append(this.time);
            sBuilder.append("\n");

            for(int i = 0; i < this.data.size(); ++i) {
                for(int j = 0; j < ((ArrayList)this.data.get(i)).size(); ++j) {
                    if (j == ((ArrayList)this.data.get(i)).size() - 1) {
                        sBuilder.append(((ArrayList)this.data.get(i)).get(j));
                    } else {
                        sBuilder.append(((ArrayList)this.data.get(i)).get(j));
                        sBuilder.append(", ");
                    }
                }

                sBuilder.append("\n");
            }

            return sBuilder.toString();
        }

    /***
     * pulls the issue type from the object
     * @return issue type as string
     */
        public String getIssueType() {
            return issueType;
        }

    /**
     * save the issue type to the object
     * @param issueType issue type as string
     */
        public void setIssueType(String issueType) {
            this.issueType = issueType;
        }

    /**
     * pulls the 2d gridarray from the reading data
     * @return 2d gridpiece array
     */
        public GridPiece[][] getGridArray() {
            return gridArray;
        }

    /**
     * saves the 2d grid piece array to the object
     * @param gridArray 2d grid piece array
     */
        public void setGridArray(GridPiece[][] gridArray) {
            this.gridArray = gridArray;
        }
    }


