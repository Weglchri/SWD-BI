package at.fhj.swd.BusinessIntelligence;

/**
 * Created by sattlerb on 24.01.2017.
 */
public class ConciseProjectmanager
{
    private String employeeName;

    public int getProjects() {
        return projects;
    }

    private int projects;

    public ConciseProjectmanager(Projectmanager projectmanager) {
        this.employeeName = projectmanager.getName();

        if(projectmanager.getInvolvedIn() > 0)
        {
            this.projects = projectmanager.getInvolvedIn();
        }
    }
}
