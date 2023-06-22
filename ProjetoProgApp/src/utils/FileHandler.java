package utils;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class FileHandler {
    public static final int RELATORIO_MENSAL = 0;
    public static final int RELATORIO_ANUAL = 1;
    public static final int ORGANIZACIONAL = 2;
    private static String path;
    private static File dir;
    private static File file;

    public static void downloadFile(int reportType, JTable table, String itemName) {
        try {
            path = "src\\planilhas";
            dir = new File(path);

            if (!dir.isDirectory()) {
                dir.mkdir();
            }

            JFileChooser seletor = new JFileChooser("src\\planilhas");
            seletor.setDialogTitle("Selecione uma pasta");
            seletor.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            seletor.showOpenDialog(null);

            path = seletor.getSelectedFile().getAbsolutePath();

            itemName = itemName.substring(0, 1).toUpperCase() + itemName.substring(1).toLowerCase();

            if (reportType == RELATORIO_MENSAL) {
                file = new File(path + "\\RelatorioMensal" + itemName + ".xls");
            } else if (reportType == RELATORIO_ANUAL) {
                file = new File(path + "\\RelatorioAnual" + itemName + ".xls");
            } else if (reportType == ORGANIZACIONAL) {
                file = new File(path + "\\Organizacao" + itemName + ".xls");
            } else
                throw new Exception("Tipo de relatorio invalido");

            if (!file.exists()) {
                file.createNewFile();
            }

            if (!file.canWrite())
                throw new Exception("Sem permissao de escrita");

            TableModel model = table.getModel();

            FileWriter fileWriter = new FileWriter(file);

            // Gravar o cabeçalho
            for (int col = 0; col < model.getColumnCount(); col++) {
                fileWriter.write(model.getColumnName(col));
                fileWriter.write("\t");
            }
            fileWriter.write("\n");

            // Gravar as linhas
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    fileWriter.write(model.getValueAt(row, col).toString());
                    fileWriter.write("\t");
                }
                fileWriter.write("\n");
            }

            fileWriter.close();

            JOptionPane.showMessageDialog(null, "Arquivo baixado com sucesso!", "Download de arquivo",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao baixar arquivo:\n" + e.getMessage(), "Download de arquivo",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro ao criar o arquivo Excel: " + e.getMessage());
        }
    }
}
