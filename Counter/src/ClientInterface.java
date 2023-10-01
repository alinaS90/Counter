import java.util.Scanner;
public class ClientInterface {
    private CounterController _controller;

    public ClientInterface()
    {
        _controller = new CounterController();
    }
    public void run()
    {
        System.out.println("Добро пожаловать в программу счётчик! \n" +
                "Введите команду '/help' для отображения списка команд");
        Scanner in = new Scanner(System.in);

        while (true)
        {
            var input = in.nextLine().toString(); ;
            if (input == null)
                continue;
            String result = _controller.executeCommand(input);
            System.out.println(result);
        }
    }
}
