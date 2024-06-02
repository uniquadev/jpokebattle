package jpokebattle.gui;

import javafx.scene.layout.GridPane;

public class GridMenu extends GridPane {

    static public class MenuOption {
        public String text;
        public boolean enabled = true;

        protected ArrowTextButton button;

        public MenuOption(String text, boolean enabled)
        {
            button = new ArrowTextButton(text, enabled);

            this.text = text;
            this.enabled = enabled;

            showArrow(false);
        }

        public void showArrow(boolean show)
        {
            button.showArrow(show);
        }
    }

    static public interface MenuCallback {
        public void onClick(MenuOption option);
    }

    private MenuOption[] options = new MenuOption[0];
    private MenuOption selected;
    private MenuCallback callback;

    public GridMenu()
    {
        super();
    }

    public void Initialize(MenuOption[] options, int cols)
    {
        assert options.length == 0;

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

        options[0].showArrow(true);
    }

    public void handleClick(MenuOption option)
    {
        if (!option.enabled)
            return;
        handleMouseHover(option);

        if (callback != null)
            callback.onClick(option);
    }

    private void handleMouseHover(MenuOption option) {
        if (selected == option)
            return;
        selected = option;
        option.showArrow(true);
        for (var opt : options) {
            if (opt == option)
                continue;
            opt.showArrow(false);
        }
    }

    public void setArrowSize(int width, int height)
    {
        for (var option : options) {
            option.button.resizeArrow(width, height);
        }
    }

    public void setCallback(MenuCallback callback)
    {
        this.callback = callback;
    }
}
