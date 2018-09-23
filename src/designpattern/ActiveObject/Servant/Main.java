package designpattern.ActiveObject.Servant;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();
        new DisplayClientThread("Chris", activeObject).start();
    }

    /**
     *
     * Servant makeString with: A
     Servant makeString with: B
     Servant displayString: Chris 0
     Alice: value =
     Bobby: value =
     Servant makeString with: A
     Alice: value = A
     Servant makeString with: B
     Bobby: value = B
     Servant makeString with: A
     Servant displayString: Chris 1
     Alice: value = AA
     Servant makeString with: B
     Servant displayString: Chris 2
     Bobby: value = BB
     Servant makeString with: A
     Servant displayString: Chris 3
     Alice: value = AAA
     Servant makeString with: B
     Servant displayString: Chris 4
     Bobby: value = BBB
     Servant makeString with: A
     Servant displayString: Chris 5
     Alice: value = AAAA
     Servant displayString: Chris 6
     Servant makeString with: B
     Servant displayString: Chris 7
     Bobby: value = BBBB
     Servant displayString: Chris 8
     Servant makeString with: A
     Servant displayString: Chris 9
     Alice: value = AAAAA
     Servant displayString: Chris 10
     Servant makeString with: B
     Servant displayString: Chris 11
     Bobby: value = BBBBB
     Servant displayString: Chris 12
     Servant displayString: Chris 13
     Servant makeString with: A
     Servant displayString: Chris 14
     Alice: value = AAAAAA
     Servant displayString: Chris 15
     Servant makeString with: B
     Servant displayString: Chris 16
     Bobby: value = BBBBBB
     Servant displayString: Chris 17
     Servant displayString: Chris 18
     Servant makeString with: A
     Servant displayString: Chris 19
     Alice: value = AAAAAAA
     Servant displayString: Chris 20
     Servant displayString: Chris 21
     Servant displayString: Chris 22
     Servant makeString with: B
     Servant displayString: Chris 23
     Bobby: value = BBBBBBB
     */
}
