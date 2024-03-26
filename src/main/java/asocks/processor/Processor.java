package asocks.processor;

import java.net.Socket;

public interface Processor {

	void process(Socket socket);
}
