package theartifex.command;

import basemod.devcommands.ConsoleCommand;

public class OptionCommand extends ConsoleCommand {

    public OptionCommand() {
        maxExtraTokens = 8; //How many additional words can come after this one. If unspecified, maxExtraTokens = 1.
        minExtraTokens = 1; //How many additional words have to come after this one. If unspecified, minExtraTokens = 0.
        requiresPlayer = true; //if true, means the command can only be executed if during a run. If unspecified, requiresplayer = false.
        simpleCheck = true;
    }
    @Override
    protected void execute(String[] strings, int i) {

    }
}
