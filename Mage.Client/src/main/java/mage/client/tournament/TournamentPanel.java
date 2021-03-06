/*
 *  Copyright 2011 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */

/*
 * TournamentPanel.java
 *
 * Created on 20-Jan-2011, 9:18:30 PM
 */

package mage.client.tournament;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import mage.client.MageFrame;
import mage.client.chat.ChatPanel;
import mage.client.util.ButtonColumn;
import mage.remote.Session;
import mage.view.RoundView;
import mage.view.TournamentGameView;
import mage.view.TournamentPlayerView;
import mage.view.TournamentView;
import org.apache.log4j.Logger;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class TournamentPanel extends javax.swing.JPanel {

    private static final Logger logger = Logger.getLogger(TournamentPanel.class);

    private UUID tournamentId;
    private Session session;
    private TournamentPlayersTableModel playersModel;
    private TournamentMatchesTableModel matchesModel;
    private UpdateTournamentTask updateTask;

    /** Creates new form TournamentPanel */
    public TournamentPanel() {
        playersModel = new TournamentPlayersTableModel();
        matchesModel = new TournamentMatchesTableModel();

        initComponents();

        tablePlayers.createDefaultColumnsFromModel();
        tableMatches.createDefaultColumnsFromModel();

        chatPanel1.useExtendedView(ChatPanel.VIEW_MODE.NONE);

        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf( e.getActionCommand() );
                UUID gameId = UUID.fromString((String)tableMatches.getValueAt(modelRow, 3));
                String state = (String)tableMatches.getValueAt(modelRow, 4);

                if (state.equals("Finished")) {
                    logger.info("Replaying game " + gameId);
                    session.replayGame(gameId);
                }
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(tableMatches, action, 6);

    }

    public synchronized void showTournament(UUID tournamentId) {
        this.tournamentId = tournamentId;
        session = MageFrame.getSession();
        MageFrame.addTournament(tournamentId, this);
        UUID chatRoomId = session.getTournamentChatId(tournamentId);
        if (session.joinTournament(tournamentId) && chatRoomId != null) {
            this.chatPanel1.connect(chatRoomId);
            startTasks();
            this.setVisible(true);
            this.repaint();
        }
        else {
            hideTournament();
        }
    }

    public void hideTournament() {
        stopTasks();
        this.chatPanel1.disconnect();
        Component c = this.getParent();
        while (c != null && !(c instanceof TournamentPane)) {
            c = c.getParent();
        }
        if (c != null) {
            ((TournamentPane)c).hideFrame();
        }
    }

    public void update(TournamentView tournament) {
        playersModel.loadData(tournament);
        matchesModel.loadData(tournament);
        this.tablePlayers.repaint();
        this.tableMatches.repaint();
    }

    public void startTasks() {
        if (session != null) {
            if (updateTask == null || updateTask.isDone()) {
                updateTask = new UpdateTournamentTask(session, tournamentId, this);
                updateTask.execute();
            }
        }
    }

    public void stopTasks() {
        if (updateTask != null)
            updateTask.cancel(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatPanel1 = new mage.client.chat.ChatPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePlayers = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMatches = new javax.swing.JTable();

        tablePlayers.setModel(this.playersModel);
        jScrollPane1.setViewportView(tablePlayers);

        tableMatches.setModel(matchesModel);
        jScrollPane2.setViewportView(tableMatches);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(chatPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
            .addComponent(chatPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mage.client.chat.ChatPanel chatPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableMatches;
    private javax.swing.JTable tablePlayers;
    // End of variables declaration//GEN-END:variables

}

class TournamentPlayersTableModel extends AbstractTableModel {
    private String[] columnNames = new String[]{"Player Name", "Points", "Results"};
    private TournamentPlayerView[] players = new TournamentPlayerView[0];

    public void loadData(TournamentView tournament) {
        players = tournament.getPlayers().toArray(new TournamentPlayerView[0]);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return players.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        switch (arg1) {
            case 0:
                return players[arg0].getName();
            case 1:
                return Integer.toString(players[arg0].getPoints());
            case 2:
                return players[arg0].getResults();
        }
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        String colName = "";

        if (columnIndex <= getColumnCount())
            colName = columnNames[columnIndex];

        return colName;
    }

    @Override
    public Class getColumnClass(int columnIndex){
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}

class TournamentMatchesTableModel extends AbstractTableModel {
    private String[] columnNames = new String[]{"Round Number", "Players", "Match Id", "Game Id", "State", "Result", "Action"};
    private TournamentGameView[] games = new TournamentGameView[0];

    public void loadData(TournamentView tournament) {
        List<TournamentGameView> views = new ArrayList<TournamentGameView>();
        for (RoundView round: tournament.getRounds()) {
            for (TournamentGameView game: round.getGames()) {
                views.add(game);
            }
        }
        games = views.toArray(new TournamentGameView[0]);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return games.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        switch (arg1) {
            case 0:
                return Integer.toString(games[arg0].getRoundNum());
            case 1:
                return games[arg0].getPlayers();
            case 2:
                return games[arg0].getMatchId().toString();
            case 3:
                return games[arg0].getGameId().toString();
            case 4:
                return games[arg0].getState();
            case 5:
                return games[arg0].getResult();
            case 6:
                if (games[arg0].getState().equals("Finished")) {
                    return "Replay";
                }
                return "";
        }
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        String colName = "";

        if (columnIndex <= getColumnCount())
            colName = columnNames[columnIndex];

        return colName;
    }

    @Override
    public Class getColumnClass(int columnIndex){
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex != 6)
            return false;
        return true;
    }

}

class UpdateTournamentTask extends SwingWorker<Void, TournamentView> {

    private Session session;
    private UUID tournamentId;
    private TournamentPanel panel;

    private static final Logger logger = Logger.getLogger(UpdateTournamentTask.class);

    UpdateTournamentTask(Session session, UUID tournamentId, TournamentPanel panel) {
        this.session = session;
        this.tournamentId = tournamentId;
        this.panel = panel;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled()) {
            this.publish(session.getTournament(tournamentId));    
            Thread.sleep(1000);
        }
        return null;
    }

    @Override
    protected void process(List<TournamentView> view) {
        panel.update(view.get(0));
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (InterruptedException ex) {
            logger.fatal("Update Tournament Task error", ex);
        } catch (ExecutionException ex) {
            logger.fatal("Update Tournament Task error", ex);
        } catch (CancellationException ex) {}
    }

}
