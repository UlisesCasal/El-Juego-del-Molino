package Services;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AddableObjectOutputStream extends ObjectOutputStream {
    public AddableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected AddableObjectOutputStream() throws IOException, SecurityException{
        super();
    }

    @Override
    protected void writeStreamHeader() throws IOException {
    }
}
