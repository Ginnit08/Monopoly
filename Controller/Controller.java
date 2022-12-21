package Controller;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Cell> listCell = new ArrayList<Cell>();
    private int row = 11;
    private int col = 11;
    private int[][] matrix;
    private int index = 0;
    
    public void controller() {
        creatMatrix();
        showMatrix();
    }
    
    public void creatMatrix() {
        matrix = new int[row][col];
        
        for (int i = col - 1; i >= 0; i--) {
            Cell cell = new Cell(row - 1, i);
            cell.setIndex(index);
            listCell.add(cell);
            matrix[cell.getY()][cell.getX()] = cell.getIndex();
            index++;
		}

        for(int i = row - 2; i >= 0; i--) {
            Cell cell = new Cell(i, 0);
            cell.setIndex(index);
            listCell.add(cell);
            matrix[cell.getY()][cell.getX()] = cell.getIndex();
            index++;
        }

        for(int i = 1; i < col; i++) {
            Cell cell = new Cell(0, i);
            cell.setIndex(index);
            listCell.add(cell);
            matrix[cell.getY()][cell.getX()] = cell.getIndex();
            index++;
        }
        
        for(int i = 1; i < col - 1; i++) {
            Cell cell = new Cell(i, col - 1);
            cell.setIndex(index);
            listCell.add(cell);
            matrix[cell.getY()][cell.getX()] = cell.getIndex();
            index++;
        }
    }

    public void showMatrix() {
        for (int i = 0; i < row ; i++) {
			for (int j = 0; j < col ; j++) {
				System.out.printf("%3d", matrix[i][j]);
			}
			System.out.println();
		}
    }

    public ArrayList<Cell> getListCell() {
        return listCell;
    }
}