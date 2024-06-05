package otan.pp;

import otan.pp.priklad.Priklad;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PrikladTableModel extends AbstractTableModel {
    List<Priklad> prikladList;
    private final String[] columnNames = {"A", "Operace", "B", "", "Vysledek", "Spravne"};

    public PrikladTableModel(List<Priklad> priklady) {
        this.prikladList = priklady;
    }

    @Override
    public int getRowCount() {
        return prikladList.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> prikladList.get(rowIndex).getA();
            case 1 -> prikladList.get(rowIndex).getOperator().getSymbol();
            case 2 -> prikladList.get(rowIndex).getB();
            case 3 -> "=";
            case 4 -> prikladList.get(rowIndex).getVysledek();
            case 5 -> prikladList.get(rowIndex).isSpravne() ? "✅" : "❌";
            default -> null;
        };
    }

     public void setPrikladList(List<Priklad> prikladList) {
        this.prikladList = prikladList;
        fireTableDataChanged();
    }
}
