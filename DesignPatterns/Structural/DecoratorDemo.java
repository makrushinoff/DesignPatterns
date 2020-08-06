package DesignPatterns.Structural;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

interface DataSource{
    String readData();
    void writeData(String data);
}

class FileDataSource implements DataSource{
    private final String filename;
    private String database;

    public FileDataSource(String filename){
        this.filename = filename;
        System.out.println("File " + filename + "was created");
    }

    @Override
    public String readData() {
        return database;
    }

    @Override
    public void writeData(String data) {
        database = data;
    }
}

class Decorator implements DataSource{
    private DataSource wrapper;

    public Decorator(DataSource datasource){
        wrapper = datasource;
    }

    @Override
    public String readData() {
       return wrapper.readData();
    }

    @Override
    public void writeData(String data) {
        wrapper.writeData(data);
    }
}

class EncryptionDecorator extends Decorator {

    public EncryptionDecorator(DataSource datasource) {
        super(datasource);
    }

    @Override
    public String readData() {
        return decode(super.readData());
    }

    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }

    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}

class CompressionDecorator extends Decorator{
    private int compLevel;

    public CompressionDecorator(DataSource datasource) {
        super(datasource);
    }

    public int getCompLevel() {
        return compLevel;
    }

    public void setCompLevel(int compLevel) {
        this.compLevel = compLevel;
    }

    @Override
    public String readData() {
        return decompress(super.readData());
    }

    @Override
    public void writeData(String data) {
        super.writeData(compress(data));
    }

    private String compress(String stringData) {
        byte[] data = stringData.getBytes();
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compLevel));
            dos.write(data);
            dos.close();
            bout.close();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException ex) {
            return null;
        }
    }
    private String decompress(String stringData) {
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            InputStream in = new ByteArrayInputStream(data);
            InflaterInputStream iin = new InflaterInputStream(in);
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            int b;
            while ((b = iin.read()) != -1) {
                bout.write(b);
            }
            in.close();
            iin.close();
            bout.close();
            return new String(bout.toByteArray());
        } catch (IOException ex) {
            return null;
        }
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        Decorator encoded = new CompressionDecorator(new EncryptionDecorator(new FileDataSource("Data")));
        encoded.writeData(salaryRecords);
        System.out.println(encoded.readData());
        DataSource plain = new FileDataSource("Data");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
