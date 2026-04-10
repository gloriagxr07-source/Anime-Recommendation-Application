package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Anime;
import model.AnimeList;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// Represents the AnimeList recommendation application
// References: https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
// SpaceInvadersBase
// SimpleDrawingPlayer
// TrafficLightGui
// https://www.youtube.com/watch?v=Kmgo00avvEw
@ExcludeFromJacocoGeneratedReport
public class AnimeListGui extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/animelist.json";
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 800;
    private static final String NAME = "My AnimeList";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private AnimeList animeList;

    private JScrollPane listPanel;
    private JButton addAnimeButton;
    private JButton addCommentButton;
    private JButton loadButton;
    private JButton saveButton;
    private JTextArea area;
    private JPanel buttonPanel;

    // EFFECTS: runs the anime application with GUI
    public AnimeListGui() {
        super("AnimeList App");
        init();
        initializeFrame();
        initializePanels();
        setVisible(true);
        updateDisplay();
        setWindowClosing();
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        animeList = new AnimeList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes JFrame
    public void initializeFrame() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: initializes JPanels
    public void initializePanels() {
        setLayout(null);

        JLabel title = new JLabel();
        title.setText(NAME);
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(500, 30, 400, 70);
        add(title);

        initializeButtonPanel();

        ImageIcon animeImg = new ImageIcon("data/line.png");

        Image scaled = animeImg.getImage().getScaledInstance(82, 800, Image.SCALE_SMOOTH);
        JLabel animeBorder = new JLabel(new ImageIcon(scaled));
        animeBorder.setBounds(200, 0, 82, 800);
        add(animeBorder);

        initializeListPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes right-side panel which displays animelist
    public void initializeListPanel() {
        area = new JTextArea(10, 15);
        area.setBackground(Color.GRAY);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 20));
        area.setForeground(Color.WHITE);

        listPanel = new JScrollPane(area);
        listPanel.setBounds(400, 200, 600, 500);
        add(listPanel);

    }

    // MODIFIES: this
    // EFFECTS: initializes left-side panel which exists buttons
    public void initializeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.setBounds(0, 0, 200, 800);

        addAnimeButton = new JButton("Add An Anime");
        addAnimeButton.setActionCommand("AddAnime");
        addCommentButton = new JButton("Add A Comment");
        addCommentButton.setActionCommand("AddComment");
        loadButton = new JButton("Load Data");
        loadButton.setActionCommand("Load");
        saveButton = new JButton("Save Data");
        saveButton.setActionCommand("Save");

        buttonPanel.add(addAnimeButton);
        buttonPanel.add(addCommentButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        add(buttonPanel);

        addAnimeButton.addActionListener(this);
        addCommentButton.addActionListener(this);
        loadButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: updates display area to show all animes in the list
    private void updateDisplay() {
        area.setText("");

        ArrayList<Anime> animes = animeList.getAnimeList();

        if (animes.isEmpty()) {
            area.setText("No anime in the list.");
            return;
        } else {
            for (Anime anime : animes) {
                area.append(anime + "\n\n");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("AddAnime")) {
            handleAddAnime();
        } else if (cmd.equals("AddComment")) {
            handleAddComment();
        } else if (cmd.equals("Load")) {
            handleLoad();
        } else if (cmd.equals("Save")) {
            handleSave();
        }

    }

    // MODIFIES: this
    // EFFECTS: promosts users to enter a name and a rating,
    // adds the anime to the list or updates its rating;
    // does noting if rating outs of range or rating is invalid
    public void handleAddAnime() {
        String name = JOptionPane.showInputDialog(this, "Anime name?");
        String ratingText = JOptionPane.showInputDialog(this, "Enter rating (0.0 ~ 5.0):");

        try {
            double rating = Double.parseDouble(ratingText);
            if (rating < 0.0 || rating > 5.0) {
                JOptionPane.showMessageDialog(this, "Rating out of range...");
                return;
            }
            animeList.addAnime(name, rating);
            JOptionPane.showMessageDialog(this, "Added " + name + " successfully!");
            updateDisplay();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid rating...");
        }
    }

    // MODIFIES: this
    // EFFECTS: promosts users to enter a name and a comment,
    // and adds the comment to the anime;
    // does nothing if anime not exists in list
    public void handleAddComment() {
        String name = JOptionPane.showInputDialog(this, " Which anime to add comment?");
        if (animeList.findAnime(name) == null) {
            JOptionPane.showMessageDialog(this, "Please enter the name of an anime that exists in the list!");
        } else {
            String comment = JOptionPane.showInputDialog(this, "Write a comment: ");
            animeList.addComment(name, comment);
            JOptionPane.showMessageDialog(this, "Finished adding comment!");
            updateDisplay();

        }

    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void handleLoad() {
        try {
            animeList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded anime list from " + JSON_STORE);
            updateDisplay();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the animelist to file
    public void handleSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(animeList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved anime list to " + JSON_STORE);
            updateDisplay();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    private void setWindowClosing() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            }
        });
    }
}
