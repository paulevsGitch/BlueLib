package paulevs.bluelib.stream;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LittleEndianDataOutputStream extends FilterOutputStream {
	int size;
	
	/**
	 * Creates an output stream filter built on top of the specified
	 * underlying output stream.
	 *
	 * @param out the underlying output stream to be assigned to
	 * the field <tt>this.out</tt> for later use, or
	 * <code>null</code> if this instance is to be
	 * created without an underlying stream.
	 */
	public LittleEndianDataOutputStream(OutputStream out) {
		super(out);
	}
	
	public void writeBoolean(boolean v) throws IOException {
		out.write(v ? 1 : 0);
		size++;
	}
	
	public void writeByte(int v) throws IOException {
		out.write(v);
		size++;
	}
	
	public void writeShort(int v) throws IOException {
		out.write(v & 255);
		out.write((v >> 8) & 255);
		size += 2;
	}
	
	public void writeChar(int v) throws IOException {
		out.write(v & 255);
		out.write((v >> 8) & 255);
		size += 2;
	}
	
	public void writeInt(int v) throws IOException {
		out.write(v & 255);
		for (byte i = 8; i < 32; i += 8) {
			out.write((v >> i) & 255);
		}
		size += 4;
	}
	
	public void writeLong(long v) throws IOException {
		out.write((int) v & 255);
		for (byte i = 8; i < 64; i += 8) {
			out.write((int) (v >> i) & 255);
		}
		size += 8;
	}
	
	public void writeFloat(float v) throws IOException {
		writeInt(Float.floatToIntBits(v));
	}
	
	public void writeBytes(byte[] v) throws IOException {
		for (int i = 0; i < v.length; i++) {
			out.write(v[i]);
		}
		size += v.length;
	}
	
	public int getSize() {
		return size;
	}
}
