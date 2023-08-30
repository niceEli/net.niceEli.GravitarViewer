package net.niceeli.gravitarviewer.gravitarviewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class GravatarApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String email = getEmailFromArgs(getParameters().getRaw().toArray(new String[0]));

        if (email == null) {
            System.out.println("Please provide an email address as a command-line argument.");
            System.exit(1);
        }

        Image gravatar = getGravatar(email);

        if (gravatar == null) {
            System.out.println("No Gravatar found for the provided email address.");
            System.exit(1);
        }

        ImageView imageView = new ImageView(gravatar);

        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Gravatar Viewer");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private String getEmailFromArgs(String[] args) {
        if (args.length > 0) {
            return args[0];
        } else {
            return null;
        }
    }

    private Image getGravatar(String email) {
        String gravatarURL = generateGravatarURL(email);

        try {
            return new Image(gravatarURL);
        } catch (Exception e) {
            return null;
        }
    }

    private String generateGravatarURL(String email) {
        String hash = md5Hex(email.toLowerCase().trim());
        return "https://www.gravatar.com/avatar/" + hash + "?s=400"; // Change size as needed.
    }

    private String md5Hex(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(message.getBytes());
            return bytesToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
