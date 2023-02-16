package com.module.DesignMode.command;

public class StereoUpCommand implements Command {
    Stereo stereo;

    public StereoUpCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
    }
}
