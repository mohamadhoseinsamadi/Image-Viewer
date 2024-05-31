import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.File;

public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton=new JButton("Choose Image");
    JButton showImageButton=new JButton("Show Image");
    JButton brightnessButton=new JButton("Brightness");
    JButton grayscaleButton=new JButton("Gray scale");
    JButton resizeButton=new JButton("Resize");
    JButton closeButton=new JButton("Exit");
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "C:\\Users\\Padidar\\Pictures";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    BufferedImage bufferedImage;
    int h = 900;
    int w = 1200;
    float brightenFactor ;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(20,200,20));


        mainPanel();
        this.setVisible(true);
    }
    JPanel mainPanel;

    public void mainPanel(){
        // Create main panel for adding to Frame
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);
        mainPanel.setBackground(Color.green);
        mainPanel.setFocusable(false);

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.setBounds(150,80,400,150);
        JLabel imageviewer_label=new JLabel("Image Viewer");
        imageviewer_label.setBounds(300,0,110,70);
        imageviewer_label.setFont(new Font("f",Font.BOLD,16));

        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        resizeButton.addActionListener(this);
        closeButton.addActionListener(this);

        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);
        mainPanel.add(imageviewer_label);

        // add main panel to our frame
        this.add(mainPanel);
    }
    JPanel resizePanel;
    public void resizePanel(){
        resizePanel = new JPanel();
        resizePanel.setLayout(null);
        JLabel resize_section=new JLabel();
        resize_section.setBounds(280,30,150,40);
        resize_section.setText("resize section");
        resize_section.setFont(new Font("d",Font.BOLD,20));
        JLabel widthlabel=new JLabel();
        widthlabel.setBounds(200,100,70,40);
        widthlabel.setText("Width");
        widthTextField=new JTextField();
        widthTextField.setBounds(250,100,200,40);
        JLabel heightlabel=new JLabel();
        heightlabel.setBounds(200,150,70,40);
        heightlabel.setText("Height");
        heightTextField=new JTextField();
        heightTextField.setBounds(250,150,200,40);


        backButton=new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBounds(10,200,70,40);
        showResizeButton=new JButton("Show Result");
        showResizeButton.addActionListener(this);
        showResizeButton.setBounds(500,200,150,40);

        resizePanel.add(resize_section);
        resizePanel.add(widthlabel);
        resizePanel.add(heightlabel);
        resizePanel.add(widthTextField);
        resizePanel.add(heightTextField);
        resizePanel.add(backButton);
        resizePanel.add(showResizeButton);

        this.add(resizePanel);

    }

    JPanel brightnessPanel;
    public void brightnessPanel(){
        brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        JLabel brightlabel=new JLabel();
        brightlabel.setBounds(100,100,400,50);
        brightlabel.setFont(new Font("m",Font.BOLD,20));
        brightlabel.setText("روشنایی را وارد کنید(باید بین 0 تا 1 باشد)");
        brightnessTextField=new JTextField();
        brightnessTextField.setBounds(400,110,200,40);
        backButton=new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBounds(10,200,70,40);
        showBrightnessButton=new JButton("Result");
        showBrightnessButton.setBounds(600,200,70,40);
        showBrightnessButton.addActionListener(this);
        brightnessPanel.add(backButton);
        brightnessPanel.add(showBrightnessButton);
        brightnessPanel.add(brightlabel);
        brightnessPanel.add(brightnessTextField);
        //brightnessPanel.setVisible(true);
        this.add(brightnessPanel);
    }

    public void chooseFileImage(){
        try {
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                bufferedImage = ImageIO.read(file);

            }
        }
        catch (Exception e){
            System.out.println("file not select correctly");
        }

    }
    JFrame tempFrame1 = new JFrame();
    JPanel tempPanel1 = new JPanel();
    ImageIcon image = new ImageIcon();
    JLabel pictureLabel = new JLabel();
    //این 2 تا رو بیرون تعریف کردم چون اگه داخل showoriginalimage تعریف میکردم، به چند تا باگ میخوردم
    //مثلا وقتی دکمه ی back رو از resizepanel میزدم و دوباره showimage رو میزدم، چند تا پنل ایجاد میشد
    public void showOriginalImage(){
        try{
            bufferedImage = ImageIO.read(file);
            image.setImage(bufferedImage);
            pictureLabel.setIcon(image);
            tempPanel1.setSize(1800, 1000);
            tempPanel1.add(pictureLabel);
            tempFrame1.setTitle("Image Viewer");
            tempFrame1.setSize(1800, 1000);
            tempFrame1.setVisible(true);
            tempFrame1.setResizable(true);
            tempFrame1.add(tempPanel1);
        }
        catch (Exception e){
            System.out.println("no Image selected !");
        }

    }
    JFrame tempFrame2 = new JFrame();
    JPanel tempPanel2 = new JPanel();
    JLabel l = new JLabel();

    public void grayScaleImage(){
        try {
            bufferedImage = ImageIO.read(file);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            BufferedImage image = op.filter(bufferedImage, null);
            ImageIcon i = new ImageIcon(image);

            l.setIcon(i);
            tempPanel2.add(l);

            tempPanel2.setSize(1000, 600);
            tempFrame2.setTitle("Image Viewer");
            tempFrame2.setSize(1800, 1000);
            tempFrame2.setVisible(true);
            tempFrame2.setResizable(true);
            tempFrame2.add(tempPanel2);
        }
        catch (Exception e){
            System.out.println("no Image selected !");
        }
    }



    public void showResizeImage(int w, int h){
        try {
            JFrame tempFrame2 = new JFrame();
            JPanel tempPanel2 = new JPanel();
            JLabel label1 = new JLabel("");
            //label1.setHorizontalAlignment(SwingConstants.CENTER);//میتوانیم این را هم اضافه کنیم(اختیاری)
            label1.setBounds(628, 28, 169, 125);
            ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(file.toString()).getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
            label1.setIcon(imageIcon1);
            tempPanel2.add(label1);
            tempPanel2.setSize(1800, 1000);
            tempFrame2.setTitle("Image Viewer");
            tempFrame2.setSize(1800, 1000);
            tempFrame2.setVisible(true);
            tempFrame2.setResizable(true);
            tempFrame2.add(tempPanel2);
        }
        catch (Exception e){
            System.out.println("file not found, please select file.");
        }

    }
    public void showBrightnessImage(float brightenFactor){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        try {
            bufferedImage = ImageIO.read(file);
        }
        catch (Exception ee){}
        RescaleOp op = new RescaleOp(brightenFactor, 0, null);
        BufferedImage image= op.filter(bufferedImage, bufferedImage);
        ImageIcon i1 = new ImageIcon(image);

        JLabel l1 = new JLabel();
        l1.setIcon(i1);
        tempPanel.add(l1);


        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            mainPanel.repaint();
            mainPanel.revalidate();
            mainPanel.setVisible(false);
            resizePanel();

        }else if(e.getSource()== showImageButton){
            showOriginalImage();

        }else if(e.getSource()==grayscaleButton){
            grayScaleImage();

        }else if(e.getSource()== showResizeButton){

            String width;
            String height;
            int w=0,h=0;
            width = widthTextField.getText();
            height=heightTextField.getText();
            w=Integer.parseInt(width);
            h=Integer.parseInt(height);
            showResizeImage(w,h);

        }else if(e.getSource()==brightnessButton){
            mainPanel.repaint();
            mainPanel.revalidate();
            mainPanel.setVisible(false);
            brightnessPanel();

        }else if(e.getSource()== showBrightnessButton){
            try {
                String roshanayee = brightnessTextField.getText();
                brightenFactor = Float.parseFloat(roshanayee);
                showBrightnessImage(brightenFactor);
            }
            catch (NullPointerException exept){
                System.out.println("ابتدا عکس را انتخاب نمایید");
            }
            catch (NumberFormatException ex){
                System.out.println(" لطفا مقدار مناسب وارد نمایید");
            }

        }else if(e.getSource()== selectFileButton){
            chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }

}