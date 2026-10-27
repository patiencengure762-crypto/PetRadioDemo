package petradiobutton;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PetRadioButton extends JFrame {

    private JRadioButton bird, cat, dog, rabbit, pig;
    private JLabel picture;

    public PetRadioButton() {
        setTitle("Pet Radio Button Demo");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel choices = new JPanel(new GridLayout(5, 1, 5, 10));
        choices.setBorder(BorderFactory.createTitledBorder("Choose a Pet"));

        bird = new JRadioButton("Bird");
        cat = new JRadioButton("Cat");
        dog = new JRadioButton("Dog");
        rabbit = new JRadioButton("Rabbit");
        pig = new JRadioButton("Pig");

        ButtonGroup group = new ButtonGroup();
        group.add(bird);
        group.add(cat);
        group.add(dog);
        group.add(rabbit);
        group.add(pig);

        choices.add(bird);
        choices.add(cat);
        choices.add(dog);
        choices.add(rabbit);
        choices.add(pig);

        picture = new JLabel(
                "Select a pet to display its picture",
                SwingConstants.CENTER
        );
        picture.setFont(new Font("Arial", Font.BOLD, 20));
        picture.setPreferredSize(new Dimension(400, 350));

        add(choices, BorderLayout.WEST);
        add(picture, BorderLayout.CENTER);

        bird.addActionListener(e -> showPet("Bird", "bird.png"));
        cat.addActionListener(e -> showPet("Cat", "cat.png"));
        dog.addActionListener(e -> showPet("Dog", "dog.png"));
        rabbit.addActionListener(e -> showPet("Rabbit", "rabbit.png"));
        pig.addActionListener(e -> showPet("Pig", "pig.png"));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showPet(String pet, String filename) {

        String relativePath = "src/petradiobutton/image/" + filename;
        File imageFile = new File(relativePath);

        if (!imageFile.exists()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Image not found!\nExpected location:\n"
                    + imageFile.getAbsolutePath(),
                    "Missing Image",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());

        if (icon.getIconWidth() <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "The image could not be loaded: " + filename,
                    "Image Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Image scaledImage = icon.getImage().getScaledInstance(
                300, 250, Image.SCALE_SMOOTH
        );

        picture.setIcon(new ImageIcon(scaledImage));
        picture.setText(pet);
        picture.setHorizontalTextPosition(SwingConstants.CENTER);
        picture.setVerticalTextPosition(SwingConstants.BOTTOM);

        JOptionPane.showMessageDialog(
                this,
                "You selected: " + pet,
                "Pet Selection",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PetRadioButton();
        });
    }
}