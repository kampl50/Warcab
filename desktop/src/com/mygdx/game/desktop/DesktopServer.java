package com.mygdx.game.desktop;

import WarcabGame.ServerClient;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import java.io.*;
import java.net.*;

public  class DesktopServer {
    public static void main (String[] args) {
        ServerClient WGS = new ServerClient();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(WGS, config);
        config.width = 1400;
        config.height = 1000;

    }
    }

