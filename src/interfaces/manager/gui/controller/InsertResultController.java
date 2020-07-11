package interfaces.manager.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import domain.element.Day;
import domain.match.Match;
import domain.team.Team;
import domain.tournament.Tournament;
import domain.tournament.TournamentType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import services.persistence.dao.impl.FacadeImpl;

public class InsertResultController implements Initializable {
	
	@FXML
	private ComboBox<String> cmbBoxTournament,cmbBoxDay;
	@FXML
	private RadioButton radioBtnGroup,radioBtnBoard;
	@FXML
	private ListView<String> listViewMatches;
	@FXML
	private Button btnAddNextDay;
	@FXML
	private CheckBox checkBoxPlayed;
	
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
		btnAddNextDay.setDisable(true);
		cmbBoxTournament.setOnAction((ActionEvent)->{
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
			matches.clear();
			int indexDay=Integer.parseInt(cmbBoxDay.getSelectionModel().getSelectedItem())-1;
			Day selectedDay=tournament.getSchedule().get(indexDay);
			btnAddNextDay.setDisable(selectedDay.isCompleted()&&radioBtnBoard.isSelected()?false:true);
			for(Match m: selectedDay.getMatchesList()){
				matches.add(String.format("%s vs. %s", m.getHomeTeam().getName().toUpperCase(),m.getAwayTeam().getName().toUpperCase()));
			}
			this.listViewMatches.setItems(matches);
			this.listViewMatches.setCellFactory(TextFieldListCell.forListView());
			this.listViewMatches.setOnMouseClicked(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					if(event.getClickCount()==2) {
						int indexMatch=listViewMatches.getSelectionModel().getSelectedIndex();
						Match match = tournament.getSchedule().get(indexDay).getMatchesList().get(indexMatch);
						checkBoxPlayed.setSelected(match.isPlayed()?true:false);
						Optional<Pair<String,String>> result=getDialogResult(match);
						result.ifPresent(score-> {
							String matchScore[]=score.getValue().trim().split("-");
							int homeScore=Integer.parseInt(matchScore[0]);
							int awayScore=Integer.parseInt(matchScore[1]);
							try {
								if (tournament.insertScore(indexDay + 1, match, homeScore, awayScore)) {
									if (facadeImpl.updateMatch(tournament.getSchedule().get(indexDay).getMatchesList().get(indexMatch))) {
										tournament.setSchedule(facadeImpl.getSchedule(tournament, false));
									}
								}
							}catch (Exception ex) {
								ex.printStackTrace();
							}
							
						});
					}
				}
				
			});
		});
		
	}
	
	private Optional<Pair<String, String>> getDialogResult(Match match) {
		dialog= new Dialog<>();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		boolean played=match.isPlayed();
		Text txtHomeTeamScore= new Text(match.getHomeTeam().getName().toUpperCase()+"'s"+" score:");
		Spinner<Integer> spinnerHomeTeamScore= getSpinner(played, match.getHomeScore());
		Text txtAwayTeamScore= new Text(match.getAwayTeam().getName().toUpperCase()+"'s"+" score:");
		Spinner<Integer> spinnerAwayTeamScore= getSpinner(played, match.getAwayScore());
		ButtonType btnTypeSaveResult= new ButtonType("Save",ButtonData.OK_DONE);
		grid.add(txtHomeTeamScore, 0,0);
		grid.add(spinnerHomeTeamScore, 1,0);
		grid.add(txtAwayTeamScore, 2,0);
		grid.add(spinnerAwayTeamScore, 3,0);
		dialog.getDialogPane().getButtonTypes().add(btnTypeSaveResult);
		dialog.getDialogPane().lookupButton(btnTypeSaveResult).setDisable(played);
		
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
			setRadioButtonsDisable(true);
		}
		else if(tournament.getTournamentType()==TournamentType.LEAGUE) {
			toggleGrp.selectToggle(radioBtnGroup);
			setRadioButtonsDisable(true);
		}
		else setRadioButtonsDisable(false);
	}
	
	private void setRadioButtonsDisable(boolean value) {
		radioBtnGroup.setDisable(value);
		radioBtnBoard.setDisable(value);
	}
	
	private Spinner<Integer> getSpinner(boolean played,int score) {
		SpinnerValueFactory <Integer> spinnerValueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,99);
		Spinner<Integer> spinner= new Spinner<>(spinnerValueFactory);
		spinner.setEditable(true);
		if(played) {
			spinner.setDisable(true);
			spinner.getValueFactory().setValue(score);
		}
		return spinner;
	}
	
	public void addNextDay(ActionEvent event) {
		System.out.println("add next day");
	}
	
}
