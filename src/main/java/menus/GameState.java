package menus;

import rules.ArenaController;

public class GameState extends State{
    public ArenaController arenaController;

    GameState(GameController g) {
        super(g);
        arenaController=new ArenaController(g.gui,g.arenaModel);
    }

    @Override
    public void init() {

    }

    @Override
    public void doStep() {
        arenaController.starting(arenaController);
    }
}
