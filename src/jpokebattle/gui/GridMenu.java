package jpokebattle.gui;

import javafx.scene.layout.GridPane;

public class GridMenu extends GridPane {

    static public class MenuOption {
        private String text;
        private boolean enabled = true;
        private int value = 0;

        protected ArrowTextButton button;

        public MenuOption(String text, boolean enabled, int value) {
            button = new ArrowTextButton(text, enabled);

            this.text = text;
            this.enabled = enabled;
            this.value = value;

            showArrow(false);
        }
        public MenuOption(String text, boolean enabled) {
            this(text, enabled, 0);
        }
        
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
            button.setText(text);
        }

        public boolean isEnabled() {
            return enabled;
        }
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
            button.getButton().setDisable(!enabled);
        }

        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }

        public void showArrow(boolean show) {
            button.showArrow(show);
        }
    }

    static public interface MenuCallback {
        public void onEvent(MenuOption option);
    }

    private MenuOption[] options = new MenuOption[0];
    private MenuOption selected;
    private MenuCallback clickCallback;
    private MenuCallback selectCallback;

    public GridMenu()
    {
        super();
    }

    public void Initialize(MenuOption[] options, int cols)
    {
        assert options.length == 0;
        this.getChildren().clear();

        this.options = options;
        for (int i = 0; i < options.length; i++) {
            var option = options[i];
            this.add(option.button, i % cols, i / cols);

            option.button.getButton().setOnMouseMoved(e -> {
                this.handleMouseHover(option);
            });
            option.button.getButton().setOnMouseClicked(e -> {
                this.handleClick(option);
            });
        }

        selectOption(options[0]);
    }

    public void handleClick(MenuOption option)
    {
        if (!option.enabled)
            return;
        handleMouseHover(option);

        if (clickCallback != null)
            clickCallback.onEvent(option);
    }

    public void selectOption(MenuOption option)
    {
        if (selected == option)
            return;
        selected = option;
        option.showArrow(true);
        for (var opt : options) {
            if (opt == option)
                continue;
            opt.showArrow(false);
        }
        if (selectCallback != null)
            selectCallback.onEvent(option);
    }

    private void handleMouseHover(MenuOption option) {
        selectOption(option);
    }

    public void setArrowSize(int width, int height)
    {
        for (var option : options) {
            option.button.resizeArrow(width, height);
        }
    }

    public void setClickCallback(MenuCallback callback)
    {
        this.clickCallback = callback;
    }

    public void setSelectCallback(MenuCallback callback)
    {
        this.selectCallback = callback;
    }
}
