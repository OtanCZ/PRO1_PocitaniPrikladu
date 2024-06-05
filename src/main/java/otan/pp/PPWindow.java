package otan.pp;

import otan.pp.priklad.Priklad;
import otan.pp.utils.CsvReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PPWindow extends JFrame {
    private List<Priklad> priklady = new ArrayList<>();
    private int selectedPriklad = -1;

    public PPWindow(int w, int h) throws HeadlessException {
        setSize(w, h);
        setTitle("Pocitani prikladu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setLayout(new BorderLayout());

        JPanel barPanel = new JPanel();
        barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.Y_AXIS));
        add(barPanel, BorderLayout.PAGE_START);

        JMenuBar mainMenuBar = new JMenuBar();
        barPanel.add(mainMenuBar);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setOrientation(JToolBar.HORIZONTAL);
        barPanel.add(toolBar);

        //table
        PrikladTableModel prikladTableModel = new PrikladTableModel(priklady);
        JTable prikladTable = new JTable(prikladTableModel);
        prikladTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        prikladTable.setPreferredScrollableViewportSize(prikladTable.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(prikladTable);
        scrollPane.setMaximumSize(new Dimension(this.getWidth(), 250));
        add(scrollPane, BorderLayout.CENTER);

        //priklad explain (I hate this)
        JPanel prikladExplain = new JPanel();
        prikladExplain.setLayout(new BoxLayout(prikladExplain, BoxLayout.Y_AXIS));
        prikladExplain.setPreferredSize(new Dimension(this.getWidth(), 250));

        JPanel prikladZadani = new JPanel();
        prikladZadani.setLayout(new BoxLayout(prikladZadani, BoxLayout.X_AXIS));
        prikladExplain.add(prikladZadani);

        JPanel prikladAPanel = new JPanel();
        prikladAPanel.setLayout(new BoxLayout(prikladAPanel, BoxLayout.Y_AXIS));
        JTextField prikladA = new JTextField();
        prikladA.setHorizontalAlignment(JTextField.CENTER);
        JLabel aLabel = new JLabel("a");
        aLabel.setHorizontalAlignment(JLabel.CENTER);
        prikladA.setEditable(false);
        prikladA.setPreferredSize(new Dimension(50, 50));
        prikladAPanel.add(aLabel);
        prikladAPanel.add(prikladA);
        prikladZadani.add(prikladAPanel);

        JPanel prikladOPanel = new JPanel();
        prikladOPanel.setLayout(new BoxLayout(prikladOPanel, BoxLayout.Y_AXIS));
        JTextField prikladO = new JTextField();
        prikladO.setHorizontalAlignment(JTextField.CENTER);
        JLabel oLabel = new JLabel("Operator");
        oLabel.setHorizontalAlignment(JLabel.CENTER);
        prikladO.setEditable(false);
        prikladO.setPreferredSize(new Dimension(50, 50));
        prikladOPanel.add(oLabel);
        prikladOPanel.add(prikladO);
        prikladZadani.add(prikladOPanel);

        JPanel prikladBPanel = new JPanel();
        prikladBPanel.setLayout(new BoxLayout(prikladBPanel, BoxLayout.Y_AXIS));
        JTextField prikladB = new JTextField();
        prikladB.setHorizontalAlignment(JTextField.CENTER);
        JLabel bLabel = new JLabel("b");
        bLabel.setHorizontalAlignment(JLabel.CENTER);
        prikladB.setEditable(false);
        prikladB.setPreferredSize(new Dimension(50, 50));
        prikladBPanel.add(bLabel);
        prikladBPanel.add(prikladB);
        prikladZadani.add(prikladBPanel);

        JPanel prikladVysledek = new JPanel();
        prikladVysledek.setLayout(new BoxLayout(prikladVysledek, BoxLayout.X_AXIS));
        prikladExplain.add(prikladVysledek);

        JPanel prikladVysledekPanel = new JPanel();
        prikladVysledekPanel.setLayout(new BoxLayout(prikladVysledekPanel, BoxLayout.Y_AXIS));
        JTextField prikladV = new JTextField();
        prikladV.setHorizontalAlignment(JTextField.CENTER);
        JLabel vLabel = new JLabel("Tvůj výsledek");
        vLabel.setHorizontalAlignment(JLabel.CENTER);
        prikladV.setEditable(false);
        prikladV.setPreferredSize(new Dimension(50, 50));
        prikladVysledekPanel.add(vLabel);
        prikladVysledekPanel.add(prikladV);
        prikladVysledek.add(prikladVysledekPanel);

        JPanel prikladSpravnyVysledekPanel = new JPanel();
        prikladSpravnyVysledekPanel.setLayout(new BoxLayout(prikladSpravnyVysledekPanel, BoxLayout.Y_AXIS));
        JTextField prikladSV = new JTextField();
        prikladSV.setHorizontalAlignment(JTextField.CENTER);
        JLabel svLabel = new JLabel("Správný výsledek");
        svLabel.setHorizontalAlignment(JLabel.CENTER);
        prikladSV.setEditable(false);
        prikladSV.setPreferredSize(new Dimension(50, 50));
        prikladSpravnyVysledekPanel.add(svLabel);
        prikladSpravnyVysledekPanel.add(prikladSV);
        prikladVysledek.add(prikladSpravnyVysledekPanel);

        JPanel prikladStatistika = new JPanel();
        prikladStatistika.setLayout(new BoxLayout(prikladStatistika, BoxLayout.X_AXIS));
        prikladExplain.add(prikladStatistika);

        JLabel pocetPrikladu = new JLabel();
        pocetPrikladu.setText("Počet příkladů: | ");
        prikladStatistika.add(pocetPrikladu);

        JLabel pocetSpravnych = new JLabel();
        pocetSpravnych.setText("Počet správných: | ");
        prikladStatistika.add(pocetSpravnych);

        JLabel pocetSpatnych = new JLabel();
        pocetSpatnych.setText("Počet špatných: ");
        prikladStatistika.add(pocetSpatnych);

        add(prikladExplain, BorderLayout.PAGE_END);

        prikladTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedPriklad = prikladTable.rowAtPoint(e.getPoint());
                Priklad selected = priklady.get(selectedPriklad);
                prikladA.setText(String.valueOf(selected.getA()));
                prikladO.setText(selected.getOperator().getSymbol());
                prikladB.setText(String.valueOf(selected.getB()));
                prikladV.setText(String.valueOf(selected.getVysledek()));
                prikladV.setBackground(selected.isSpravne() ? Color.GREEN : Color.RED);
                prikladSV.setText(String.valueOf(selected.getSpravnyVysledek()));
            }
        });

        //file loading stuff
        JMenu fileMenu = new JMenu("File");
        mainMenuBar.add(fileMenu);

        JMenuItem openProject = new JMenuItem("Open project");
        openProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        openProject.addActionListener(click -> {
            JFileChooser openChooser = new JFileChooser();
            openChooser.setDialogTitle("Choose file to open");
            openChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
            openChooser.setFileFilter(filter);

            int result = openChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                CsvReader csvReader = new CsvReader();
                String selectedFilePath = openChooser.getSelectedFile().getAbsolutePath();
                priklady = csvReader.loadCsv(selectedFilePath);
                prikladTableModel.setPrikladList(priklady);
                pocetPrikladu.setText("Počet příkladů: " + priklady.size() + " | ");
                int spravnych = 0;
                for (Priklad p : priklady) {
                    if(p.isSpravne()) spravnych++;
                }
                pocetSpravnych.setText("Počet správných: " + spravnych + " | ");
                pocetSpatnych.setText("Počet špatných: " + (priklady.size() - spravnych));
            }
        });
        fileMenu.add(openProject);
    }
}
