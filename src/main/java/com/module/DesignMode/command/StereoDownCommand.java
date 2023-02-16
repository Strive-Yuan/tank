package com.module.DesignMode.command;

public class StereoDownCommand implements Command {
    Stereo stereo;

    public StereoDownCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }
}
