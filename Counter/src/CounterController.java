import java.text.MessageFormat;

public class CounterController {
    private CounterModel _counterModel;
    private CounterManager _counterManager;
    private boolean _isRunning;

    public CounterController()
    {
        _counterManager = new CounterManager();
        _counterModel = new CounterModel();
    }
    public String executeCommand(String command)
    {
        String message = null;
        switch (command){
            case "/help":
                message = commandHelp();
                break;
            case "/start":
                message = commandStart();
                break;
            case "/inc":
                message = commandInc();
                break;
            case "/reset":
                message = commandReset();
                break;
            case "/stop":
                message = commandStop();
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append(MessageFormat.format("Введена неизвестная команда {0}! \n",command));
                sb.append("Используйте команду '/help', чтобы увидеть список доступных комманд");
                message = sb.toString();
                break;
        }
        return message;
    }

    private String commandStart()
    {
        String message = null;
        if(_isRunning)
        {
            message = "Счётчик уже запущен!";
            return message;
        }
        else
        {
            _isRunning = true;
            StringBuilder sb = new StringBuilder();
            sb.append("Запускаю счётчик.. \n");

            var counter = _counterManager.loadCounter();
            if(counter != null)
            {
                _counterModel = counter;
                sb.append(MessageFormat.format("Счётчик загружен, значение = {0}", _counterModel.Count));
            }
            else
            {
                sb.append(MessageFormat.format("Значение счётчика = {0}", _counterModel.Count));
            }
            message = sb.toString();
            return message;
        }
    }
    private String commandStop()
    {
        String message = null;
        if (_isRunning)
        {
            _isRunning = false;
            StringBuilder sb = new StringBuilder();
            sb.append(MessageFormat.format("Значение счётчика = {0} \n", _counterModel.Count));
            sb.append("Завершаю работу..");
            message = sb.toString();

            _counterManager.saveCounter(_counterModel);
            return message;
        }
        else
        {
            message = "Cчётчик не запущен! Введите комманду '/start' для запуска";
            return message;
        }
    }
    private String commandInc()
    {
        String message = null;
        if (_isRunning)
        {
            _counterModel.Count++;
            StringBuilder sb = new StringBuilder();
            sb.append("Инкрементирую значение счётчика \n");
            sb.append(MessageFormat.format("Новое значение счётчика = {0}", _counterModel.Count));
            message = sb.toString();
            return message;
        }
        else
        {
            message = "Cчётчик не запущен! Введите комманду '/start' для запуска";
            return message;
        }
    }
    private String commandReset()
    {
        String message = null;
        if (_isRunning)
        {
            _counterModel.Count = 0;
            StringBuilder sb = new StringBuilder();
            sb.append("Сбрасываю значение счётчика \n");
            sb.append(MessageFormat.format("Новое значение счётчика = {0}", _counterModel.Count));
            message = sb.toString();
            return message;
        }
        else
        {
            message = "Cчётчик не запущен! Введите комманду '/start' для запуска";
            return message;
        }
    }
    private String commandHelp()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Доступные комманды для работы с счётчиком: \n");
        sb.append("'/start' - запускает работу счётчика \n");
        sb.append("'/stop' - завершает работу счётчика \n");
        sb.append("'/inc' - увеличивает значение счётчика на 1 \n");
        sb.append("'/reset' - сбрасывает значение счётчика счётчика до 0");
        return sb.toString();
    }
}
