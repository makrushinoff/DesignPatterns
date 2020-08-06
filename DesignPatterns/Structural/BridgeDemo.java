package DesignPatterns.Structural;

interface Device{
    void enable();
    void disable();
    double getVolume();
    void increaseVolume(double percent);
    void decreaseVolume(double percent);
    int getChannel();
    void setChannel(int channel);

}

class TV implements Device{
    private int channel;
    private double volume;
    private boolean isEnabled = false;

    public TV(){
        channel = 21;
        volume = 45;
    }

    @Override
    public void enable() {
        isEnabled = true;
    }

    @Override
    public void disable() {
        if(!isEnabled)
            isEnabled = false;
        else
            System.out.println("TV is already disabled.");;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public void increaseVolume(double percent) {
        volume += percent;
    }

    @Override
    public void decreaseVolume(double percent) {
        volume -= percent;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }
}

class Remote{
    protected Device device;

    public Remote(Device device){
        this.device = device;
    }

    public void nextChannel(){
        device.setChannel(device.getChannel() + 1);
    }

    public void prevChannel(){
        device.setChannel(device.getChannel() - 1);
    }

    public void volumeUp(){
        device.increaseVolume(5);
    }

    public void volumeDown(){
        device.decreaseVolume(5);
    }

    public void disableDevice(){
        device.disable();
    }

    public void enableDevice(){
        device.enable();
    }
}

public class BridgeDemo {
    public static void main(String[] args) {
        TV tv = new TV();
        Remote remote = new Remote(tv);
        remote.enableDevice();
        remote.nextChannel();
        remote.volumeUp();
        remote.volumeUp();
        remote.disableDevice();
    }
}
