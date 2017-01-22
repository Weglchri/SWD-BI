package at.fhj.swd.EntityCreator;

import javax.persistence.EntityManager;


public class EntityCreator {

    public EntityCreator(final EntityManager entityManager)
    {
        setEntityManager( entityManager );
    }

    protected EntityManager entityManager;

    private void setEntityManager(final EntityManager entityManager)
    {
        if(entityManager == null)
            throw new IllegalArgumentException("EntityManager is invalid!");

        this.entityManager = entityManager;
    }

}
