package com.jeremydyer.hdpmvn.core;

import com.jeremydyer.hdpmvn.VersionUpdate;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jdyer on 3/4/16.
 */
public abstract class HortonworksRelease {

    private Date releaseDate;
    private String releaseNotes;
    private ArrayList<VersionUpdate> versionUpdates;    //Holds the list of compoents that were updated in this release from its parent
}
