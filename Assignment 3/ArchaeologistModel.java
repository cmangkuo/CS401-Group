

public class ArchaeologistModel {
	private SiteModel site;
	private boolean free;
	
	public ArchaeologistModel() {
		free = true;
	}
	
	public SiteModel getSite() {
		return site;
	}
	
	public void setSite(SiteModel model) {
		site = model;
		free = false;
	}
	
	public boolean getFree() {
		return free;
	}
	
	public boolean setFree(boolean freedom) {
		free = freedom;
	}
}
