package sweeper;

class Flag {
    private Matrix flagMap;
    private int totalFlags;
    private int countOfClosetBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosetBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosetBoxes--;
    }

    void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    private void setClosetToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED:
                setClosetToBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    int getCountOfClosetBoxes() {
        return countOfClosetBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToClosetBombBox(Coord coord) {
        if(flagMap.get(coord) == Box.CLOSED){
            flagMap.set(coord, Box.OPENED);
        }
    }

    void setNobombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED){
            flagMap.set(coord, Box.NOBOMB);
        }
    }

    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for(Coord around : Ranges.getCoordsAround(coord)){
            if (flagMap.get(around) == Box.FLAGED){
                count++;
            }
        }
        return count;
    }
}
