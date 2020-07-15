package services.persistence.dao;

import services.persistence.dao.impl.FacadeImpl;

/**
 * Interface for FacadeImpl
 * @see FacadeImpl
 * @see ITeamDAO
 * @see IElementDAO
 * @see ITournamentDAO
 * @see IManagerDAO
 */
public interface IFacade extends ITeamDAO, IElementDAO, ITournamentDAO, IManagerDAO {

}
