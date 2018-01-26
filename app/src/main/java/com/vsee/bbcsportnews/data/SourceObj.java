package com.vsee.bbcsportnews.data;

/**
 * Created by Tien on 1/26/2018.
 */

public class SourceObj {
    private String id;
    private String name;

    public SourceObj() {
        id = name = "";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id = " + id
                + ",name = " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SourceObj){
            SourceObj comparedSource = (SourceObj)obj;
            return this.id.equals(comparedSource.getId())
                    && this.name.equals(comparedSource.getName());
        }
        return false;
    }
}
