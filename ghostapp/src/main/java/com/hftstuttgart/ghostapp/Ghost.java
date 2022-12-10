package com.hftstuttgart.ghostapp;

public class Ghost {

    private String name;
    private GhostType GhostType;
    private ThreadLevel threadlevel;

    public Ghost(){
    }

    public Ghost(String name, com.hftstuttgart.ghostapp.GhostType ghostType, ThreadLevel threadlevel) {
        this.name = name;
        GhostType = ghostType;
        this.threadlevel = threadlevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.hftstuttgart.ghostapp.GhostType getGhostType() {
        return GhostType;
    }

    public void setGhostType(com.hftstuttgart.ghostapp.GhostType ghostType) {
        GhostType = ghostType;
    }

    public ThreadLevel getThreadlevel() {
        return threadlevel;
    }

    public void setThreadlevel(ThreadLevel threadlevel) {
        this.threadlevel = threadlevel;
    }
}
