package rs.ac.uns.ftn.siit.sw442019.graduate.util;


import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityUpdateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.*;

public class PictureHandler {

    private static String generateBase64String(final byte[] pictureData) {

        return Base64.getEncoder().encodeToString(pictureData);
    }

    public static String generatePhotoPath(final String name) {

        return PHOTOS_FILE_PATH + name;
    }

    public static String generateSavePhotoPath(final String name) {

        return TARGET_PHOTO_FILE_PATH + name;
    }

    public static String generatePhotoName(final Long id, final int numberOfPicture) {

        return String.format("offer-%s-%d.png", id.toString(), numberOfPicture);
    }

    public static byte[] getPictureDataByName(final String name) throws IOException
    {
        return Files.readAllBytes(Paths.get(generatePhotoPath(name)));
    }

    public static String convertPictureToBase64ByName(final String name) {
        try {
            if (name.length() > 20){

                return name;
            }
            byte[] pictureData = getPictureDataByName(name);

            return generateBase64String(pictureData);
        } catch (IOException e) {
            return "";
        }
    }

    private static void savePictureFromBase64(final String pictureName, final String base64) throws EntityUpdateException {
        try{
            byte[] image = Base64.getDecoder().decode(base64);
            File file = new File(generateSavePhotoPath(pictureName));
            file.createNewFile();
            OutputStream out = new FileOutputStream(file);
            out.write(image);
            out.flush();
            out.close();

        } catch (Exception e) {
            throw new EntityUpdateException("Profile picture update failed, try again later.");
        }
    }


    public static String savePicture(final String base64Opt, final Long id, final int numberOfPicture) throws EntityUpdateException {
        String pictureName = generatePhotoName(id, numberOfPicture);
        savePictureFromBase64(pictureName, base64Opt);

        return pictureName;
    }
}
