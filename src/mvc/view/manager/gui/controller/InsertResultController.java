package mvc.view.manager.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import database.dao.impl.FacadeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;
import mvc.model.element.Day;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;
import mvc.model.tournament.TournamentType;
import mvc.model.match.Match;

public class InsertResultController implements Initializable {
	
	@FXML
	private ComboBox<String> cmbBoxTournament,cmbBoxDay;
	@FXML
	private RadioButton radioBtnGroup,radioBtnBoard;
	@FXML
	private ListView<String> listViewMatches;
	
	private ToggleGroup toggleGrp;
	private ObservableList<String> tournaments,days,matches;
	private Dialog<Pair<String, String>> dialog; //= new Dialog<>();
	private String username;
	private FacadeImpl facadeImpl;
	private List<Tournament> tournamentsList;
	private Tournament tournament;
	//private List<Day> daysList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		toggleGrp= new ToggleGroup();
		radioBtnGroup.setToggleGroup(toggleGrp);
		radioBtnBoard.setToggleGroup(toggleGrp);
		tournaments=FXCollections.observableArrayList();
		days=FXCollections.observableArrayList();
		matches=FXCollections.observableArrayList();
		facadeImpl= FacadeImpl.getInstance();
		cmbBoxTournament.setOnAction((ActionEvent)->{
//			radioBtnGroup.setSelected(false);
//			radioBtnBoard.setSelected(false);
			tournament=getTournament(cmbBoxTournament.getSelectionModel().getSelectedItem());
			radioBtnAutoSelection(tournament);
			try {
				days.clear();
				tournament.setSchedule(facadeImpl.getSchedule(tournament,false));
				for(Day d:tournament.getSchedule())
					days.add(Integer.toString(d.getNumber()));
				cmbBoxDay.setItems(days);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		});
		cmbBoxDay.setOnAction((ActionEvent)->{
			//dayNumber=cmbBoxDay.getSelectionModel().getSelectedItem()
			matches.clear();
			int indexDay=Integer.parseInt(cmbBoxDay.getSelectionModel().getSelectedItem())-1;
			for(Match m: tournament.getSchedule().get(indexDay).getMatchesList()){
				matches.add(String.format("%s vs. %s", m.getHomeTeam().getName().toUpperCase(),m.getAwayTeam().getName().toUpperCase()));
			}
			this.listViewMatches.setItems(matches);
			this.listViewMatches.setCellFactory(TextFieldListCell.forListView());
			this.listViewMatches.setOnMouseClicked(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					int indexMatch=listViewMatches.getSelectionModel().getSelectedIndex();
					Match oldMatch = tournament.getSchedule().get(indexDay).getMatchesList().get(indexMatch);
					Optional<Pair<String,String>> result=getDialogResult();
					result.ifPresent(score-> {
						String matchScore[]=score.getValue().trim().split("-");
						int homeScore=Integer.parseInt(matchScore[0]);
						int awayScore=Integer.parseInt(matchScore[1]);
						try {
							if (tournament.insertScore(indexDay + 1, oldMatch,homeScore, awayScore)) {
								if (facadeImpl.updateMatch(oldMatch, tournament.getSchedule().get(indexDay).getMatchesList().get(indexMatch))) {
									tournament.setSchedule(facadeImpl.getSchedule(tournament, false));
								}
							}
						}catch (Exception ex) {
							ex.printStackTrace();
						}
					});
					
				}
				
			});
		});
		
	}
	
	private Optional<Pair<String, String>> getDialogResult() {
		dialog= new Dialog<>();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		Text txtHomeTeamScore= new Text("Home team score:");
		SpinnerValueFactory<Integer> homeNumbers= new SpinnerValueFactory.IntegerSpinnerValueFactory(0,99);
		Spinner<Integer> spinnerHomeTeamScore= new Spinner<>(homeNumbers);
		spinnerHomeTeamScore.setEditable(true);
//		ComboBox<Integer> cmbBoxHomeTeamScore=new ComboBox<Integer>();
//		cmbBoxHomeTeamScore.setPromptText("Score Home Team");
//		ComboBox<Integer> cmbBoxAwayTeamScore=new ComboBox<Integer>();
//		cmbBoxAwayTeamScore.setPromptText("Score Away Team");
		Text txtAwayTeamScore= new Text("Away team score:");
		SpinnerValueFactory<Integer> awayNumbers= new SpinnerValueFactory.IntegerSpinnerValueFactory(0,99);
		Spinner<Integer> spinnerAwayTeamScore= new Spinner<>(awayNumbers);
		spinnerAwayTeamScore.setEditable(true);	
		ButtonType btnTypeSaveResult= new ButtonType("Save",ButtonData.OK_DONE);

		grid.add(txtHomeTeamScore, 0,0);
		grid.add(spinnerHomeTeamScore, 1,0);
		grid.add(txtAwayTeamScore, 2,0);
		grid.add(spinnerAwayTeamScore, 3,0);
		dialog.getDialogPane().getButtonTypes().add(btnTypeSaveResult);
		
		dialog.getDialogPane().setContent(grid);
		
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == btnTypeSaveResult ) {
		        return new Pair<>("result",String.format("%d-%d",spinnerHomeTeamScore.getValue(),spinnerAwayTeamScore.getValue()));
		    }
		    return null;
		});
		return dialog.showAndWait();
	}
	
	public void setUsername(String username) {		
		this.username=username;
	}
	
	public void setTournamentsList(List<Tournament> tournamentsList) {
		this.tournamentsList=tournamentsList;
	}
	
	public void populateCmbBoxTournament(ObservableList<String> tournaments) {
		this.tournaments=tournaments;
		this.cmbBoxTournament.setItems(tournaments);
	}
	
	private Tournament getTournament(String name) {
		for(Tournament tournament:tournamentsList) {
			if(tournament.getName().equals(name))
				return tournament;
		}
		return null;
	}
	
	private void radioBtnAutoSelection(Tournament tournament) {
		if(tournament.getTournamentType()==TournamentType.KNOCKOUT_PHASE) {
			toggleGrp.selectToggle(radioBtnBoard);
			radioButtonsSetDisable();
		}
		else if(tournament.getTournamentType()==TournamentType.LEAGUE) {
			toggleGrp.selectToggle(radioBtnGroup);
			radioButtonsSetDisable();
		}
	}
	
	private void radioButtonsSetDisable() {
		radioBtnGroup.setDisable(true);
		radioBtnBoard.setDisable(true);
	}
	
}
