import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValorWorld extends Board {

    Random random = new Random();

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;

    Map<List<Integer>, Market> marketMapping = new HashMap<>();
    Map<List<Integer>, Hero> heroMapping = new HashMap<>();
    Map<List<Integer>, Monster> monsterMapping = new HashMap<>();

    public Cell[][] getValorWorld() {
        return this.valorWorld;
    }

    public void setValorWorld(Cell[][] valorWorld) {
        this.valorWorld = valorWorld;
    }

    private Cell[][] valorWorld;

    public ValorWorld() {
        setSize(8);
        this.valorWorld = new Cell[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if ((j + 1) % 3 == 0) {
                    valorWorld[i][j] = new InaccessibleCell();
                } else if ((i == 0 || i == this.size-1) && valorWorld[i][j] == null) {
                    valorWorld[i][j] = new NexusCell(new Mark("  "), new Mark("  "));
                } else {
                    char cellType = random.setValorWorldCellType();
                    switch (cellType){
                        case 'P':
                            valorWorld[i][j] = new PlainCell();
                            break;
                        case 'B':
                            valorWorld[i][j] = new BushCell();
                            break;
                        case 'K':
                            valorWorld[i][j] = new KoulouCell();
                            break;
                        case 'C':
                            valorWorld[i][j] = new CaveCell();
                            break;
                    }
                }
            }
        }
    }


    public void printWorld() {
        for (int i = 0; i < this.size; i++) {
            printRow(i);
        }
    }

    public void printRow(int row) {
        //prints a row of the board
        StringBuilder border = new StringBuilder();
        StringBuilder cellContents = new StringBuilder();
        for (int j = 0; j < 8; j++) {
            Cell cell = valorWorld[row][j];
            border.append(cell.getBorder()).append(" ");
            cellContents.append(cell.getCellContents()).append(" ");
        }
        System.out.println(border);
        System.out.println(cellContents);
        System.out.println(border);
        System.out.println();
    }

//    public void printCurrentBoardState() {
//        for (int i = 0; i < this.size; i++) {
//            for (int j = 0; j < this.size; j++) {
//                System.out.print(valorWorld[i][j].getCellContents() + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        new ValorWorld().printWorld();
//    }
}


