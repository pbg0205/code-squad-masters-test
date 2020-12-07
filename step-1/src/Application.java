class Application extends Thread {
    @Override
    public void run() {
        String[] commands;
        boolean isValid;

        do {
            commands = InputView.inputCommands();
            isValid = new InputValidator(commands).validate();
        } while (!isValid);

        new WordPusher(commands).pushWord();
    }
}