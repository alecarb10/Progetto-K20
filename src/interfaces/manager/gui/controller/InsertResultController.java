package interfaces.manager.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import domain.element.Day;
import domain.match.Match;
import domain.team.Player;
import domain.team.Stadium;
import domain.team.Team;
import domain.tournament.Tournament;
import domain.tournament.TournamentType;
import interfaces.manager.gui.util.GraphicControlsHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;
import services.persistence.dao.impl.FacadeImpl;

/**
 * Controller for insert the results of matches that played into a particular tournament
 * 
 * @see Initializable
 * @see Tournament
 * @see Match
 * @see Day
 * @see Player
 * @see Stadium
 */

public class InsertResultController implements Initializable {

	@FXML
	private ComboBox<String> cmbBoxTournament, cmbBoxDay;
	@FXML
	private RadioButton radioBtnGroup, radioBtnBoard;
	@FXML
	private ListView<String> listViewMatches;

	private String username;
	private ObservableList<String> tournaments, days, matches;
	private Dialog<Pair<String, String>> dialog;
	private ToggleGroup toggleGrp;
	private FacadeImpl facadeImpl;
	private List<Tournament> tournamentsList;
	private Tournament tournament;
	private Match match;
	private int indexDay;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		toggleGrp = new ToggleGroup();
		radioBtnGroup.setToggleGroup(toggleGrp);
		radioBtnBoard.setToggleGroup(toggleGrp);
		tournaments = FXCollections.observableArrayList();
		days = FXCollections.observableArrayList();
		matches = FXCollections.observableArrayList();
		facadeImpl = FacadeImpl.getInstance();
		toggleGrp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (toggleGrp.getSelectedToggle() != null) {
					try {
						days.clear();
						if (radioBtnGroup.isSelected()) {
							tournament.setGroupSchedule(facadeImpl.getGroupSchedule(tournament));
							for (Day d : tournament.getGroupSchedule())
								days.add(Integer.toString(d.getNumber()));
						} else if (radioBtnBoard.isSelected()) {
							try {
								if (!radioBtnBoard.isDisable())
									tournament.setGroupSchedule(facadeImpl.getGroupSchedule(tournament));
								tournament.setBoardSchedule(facadeImpl.getBoardSchedule(tournament));
								for (Day d : tournament.getBoardSchedule())
									days.add(Integer.toString(d.getNumber()));
							} catch (NullPointerException npe) {
								new Alert(AlertType.ERROR, "Group is not completed yet.", ButtonType.OK).show();
							}
						}
						cmbBoxDay.setItems(days);

					} catch (Exception e) {
						new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
					}
				}
			}
		});
	}

	private Optional<Pair<String, String>> getDialogResult(Match match) {
		dialog = new Dialog<>();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		boolean played = match.isPlayed();
		Text txtHomeTeamScore = new Text(match.getHomeTeam().getName().toUpperCase() + "'s" + " score:");
		Spinner<Integer> spinnerHomeTeamScore = getSpinner(played, match.getHomeScore());
		Text txtAwayTeamScore = new Text(match.getAwayTeam().getName().toUpperCase() + "'s" + " score:");
		Spinner<Integer> spinnerAwayTeamScore = getSpinner(played, match.getAwayScore());
		ButtonType btnTypeSaveResult = new ButtonType("Save", ButtonData.OK_DONE);
		grid.add(txtHomeTeamScore, 0, 0);
		grid.add(spinnerHomeTeamScore, 1, 0);
		grid.add(txtAwayTeamScore, 2, 0);
		grid.add(spinnerAwayTeamScore, 3, 0);
		dialog.getDialogPane().getButtonTypes().add(btnTypeSaveResult);
		dialog.getDialogPane().lookupButton(btnTypeSaveResult).setDisable(played);
		dialog.getDialogPane().setContent(grid);
		dialog.setResultConverter(dialogButton -> {
				if (dialogButton == btnTypeSaveResult)
					return new Pair<>("result", String.format("%d-%d", spinnerHomeTeamScore.getValue(), spinnerAwayTeamScore.getValue()));
			return null;
		});
		return dialog.showAndWait();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setTournamentsList(List<Tournament> tournamentsList) {
		this.tournamentsList = tournamentsList;
	}

	public void populateCmbBoxTournament(ObservableList<String> tournaments) {
		this.tournaments = tournaments;
		this.cmbBoxTournament.setItems(tournaments);
	}

	private Tournament getTournament(String name) {
		for (Tournament tournament : tournamentsList) {
			if (tournament.getName().equals(name))
				return tournament;
		}
		return null;
	}

	private void radioBtnAutoSelection(Tournament tournament) {
		if (tournament.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
			toggleGrp.selectToggle(radioBtnBoard);
			setRadioButtonsDisable(true);
		} else if (tournament.getTournamentType() == TournamentType.LEAGUE) {
			toggleGrp.selectToggle(radioBtnGroup);
			setRadioButtonsDisable(true);
		} else
			setRadioButtonsDisable(false);
	}

	private void setRadioButtonsDisable(boolean value) {
		radioBtnGroup.setDisable(value);
		radioBtnBoard.setDisable(value);
	}

	private Spinner<Integer> getSpinner(boolean played, int score) {
		SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
		Spinner<Integer> spinner = new Spinner<>(spinnerValueFactory);
		spinner.setEditable(true);
		if (played) {
			spinner.setDisable(true);
			spinner.getValueFactory().setValue(score);
		}
		return spinner;
	}

	public void setToggleGrp(ActionEvent event) {
		GraphicControlsHandler.resetComboBox(cmbBoxDay, "Select Day");
		days.clear();
		matches.clear();
		if (toggleGrp.getSelectedToggle() != null)
			toggleGrp.getSelectedToggle().setSelected(false);
		tournament = getTournament(cmbBoxTournament.getSelectionModel().getSelectedItem());
		radioBtnAutoSelection(tournament);
	}

	public void populateListViewMatches(ActionEvent event) {
		matches.clear();
		Day selectedDay = null;
		if (cmbBoxDay.getValue() != null) {
			indexDay = Integer.parseInt(cmbBoxDay.getSelectionModel().getSelectedItem()) - 1;
			if (radioBtnGroup.isSelected())
				selectedDay = tournament.getGroupSchedule().get(indexDay);
			else if (radioBtnBoard.isSelected())
				selectedDay = tournament.getBoardSchedule().get(indexDay);
			for (Match m : selectedDay.getMatchesList()) {
				matches.add(String.format("%s vs. %s", m.getHomeTeam().getName().toUpperCase(),
						m.getAwayTeam().getName().toUpperCase()));
			}
			listViewMatches.setItems(matches);
			listViewMatches.setCellFactory(TextFieldListCell.forListView());
			listViewMatches.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (event.getClickCount() == 2) {
						insertResult();
					}

				}
			});
		}

	}

	public void insertResult() {
		int indexMatch = listViewMatches.getSelectionModel().getSelectedIndex();
		if (radioBtnGroup.isSelected())
			match = tournament.getGroupSchedule().get(indexDay).getMatchesList().get(indexMatch);
		else if (radioBtnBoard.isSelected())
			match = tournament.getBoardSchedule().get(indexDay).getMatchesList().get(indexMatch);
		Optional<Pair<String, String>> result = getDialogResult(match);
		result.ifPresent(score -> {
			try {
				String matchScore[] = score.getValue().trim().split("-");
				int homeScore = Integer.parseInt(matchScore[0]);
				int awayScore = Integer.parseInt(matchScore[1]);
				int scheduleSize = -1;
				if (radioBtnBoard.isSelected()) {
					scheduleSize = tournament.getBoardSchedule().size();
					if (homeScore == awayScore) {
						new Alert(AlertType.ERROR, "You can't insert a draw in board.", ButtonType.OK).show();
						dialog.close();
						return;
					}
				}
				if (tournament.insertScore(indexDay + 1, match, homeScore, awayScore)) {
					if (radioBtnGroup.isSelected())
						storeGroup(indexMatch);
					else if (radioBtnBoard.isSelected())
						storeBoard(indexMatch, scheduleSize);
				}
			} catch (Exception e) {
				new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
			}

		});
	}

	private void storeGroup(int indexMatch) throws SQLException {
		if (facadeImpl.updateMatch(tournament.getGroupSchedule().get(indexDay).getMatchesList().get(indexMatch))) {
			tournament.setGroupSchedule(facadeImpl.getGroupSchedule(tournament));
			if (tournament.getGroup().isCompleted() && !radioBtnGroup.isDisable()) {
				tournament.addTeams(facadeImpl.getTeamsByTournament(tournament));
				tournament.initBoard(tournament.getTeamsList());
				facadeImpl.storeBoard(tournament);
				tournament.getBoard().setId(facadeImpl.getLastElementID(tournament.getBoard()));
				for (Team team : tournament.getTeamsList())
					facadeImpl.updateTeam(tournament, team);
				facadeImpl.storeSchedule(tournament.getBoardSchedule(), tournament);
				new Alert(AlertType.INFORMATION,"Board started.",ButtonType.OK).show();
			}
		}
	}

	private void storeBoard(int indexMatch, int scheduleSize) throws SQLException {
		if (facadeImpl.updateMatch(tournament.getBoardSchedule().get(indexDay).getMatchesList().get(indexMatch))) {
			if (tournament.getBoardSchedule().size() > scheduleSize) {
				tournament.getBoard().setId(facadeImpl.getBoardIDByTournament(tournament));
				if (facadeImpl.storeDay(tournament.getBoardSchedule().get(tournament.getBoardSchedule().size() - 1),
						tournament)) {
					days.clear();
					for (Day d : tournament.getBoardSchedule())
						days.add(Integer.toString(d.getNumber()));
					cmbBoxDay.getSelectionModel().selectLast();
				}

			}
			tournament.setBoardSchedule(facadeImpl.getBoardSchedule(tournament));
		}
	}

}
