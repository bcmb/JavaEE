package com.insart.beans;

import java.io.File;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;

@Local
public interface FSExplorer {
	public List<String> getAllFiles(File fs);
}
