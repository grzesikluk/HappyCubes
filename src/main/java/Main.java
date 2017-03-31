import cubesets.CubePrimarySetFactory;
import cubesets.ElementSet;
import cubesets.SetColor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        /* Check arguments */
        if (args.length == 0) {
            System.out.println(getHelp());
            System.exit(0);
        }

        SetColor color = convertArgToColor(args[0]);
        Path path = Paths.get(color + ".txt");

        /*write solution to file*/
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(new SolutionHelper().getAllSolutions(new ElementSet(CubePrimarySetFactory.getSet(color))).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static SetColor convertArgToColor(String arg) {
        SetColor color;

        switch (arg) {
            case ("blue"):
                color = SetColor.BLUE;
                break;
            case ("yellow"):
                color = SetColor.YELLOW;
                break;
            case ("red"):
                color = SetColor.RED;
                break;
            case ("purple"):
                color = SetColor.PURPLE;
                break;
            default:
                color = SetColor.BLUE;
        }
        return color;

    }

    private static String getHelp() {
        return "Usage: Main [blue|red|yellow|purple]";
    }


}
