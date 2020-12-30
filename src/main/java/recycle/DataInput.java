package recycle;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataInput extends DataInputStream {
  private char[] chars = new char[32];
  
  public DataInput(InputStream paramInputStream) { super(paramInputStream); }
  
  private void readUtf8_slow(int paramInt1, int paramInt2, int paramInt3) {
    char[] arrayOfChar = this.chars;
    while (true) {
      switch (paramInt3 >> 4) {
        default:
          if (++paramInt2 >= paramInt1)
            return; 
          break;
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
          arrayOfChar[paramInt2] = (char)paramInt3;
        case 12:
        case 13:
          arrayOfChar[paramInt2] = (char)((paramInt3 & 0x1F) << 6 | readdata() & 0x3F);
        case 14:
          arrayOfChar[paramInt2] = (char)((paramInt3 & 0xF) << 12 | (readdata() & 0x3F) << 6 | readdata() & 0x3F);
      } 
      paramInt3 = readdata() & 0xFF;
    } 
  }
  
  public int readInt(boolean paramBoolean) {
    int k = readdata();
    int j = k & 0x7F;
    int i = j;
    if ((k & 0x80) != 0) {
      k = readdata();
      j |= (k & 0x7F) << 7;
      i = j;
      if ((k & 0x80) != 0) {
        k = readdata();
        j |= (k & 0x7F) << 14;
        i = j;
        if ((k & 0x80) != 0) {
          k = readdata();
          j |= (k & 0x7F) << 21;
          i = j;
          if ((k & 0x80) != 0)
            i = j | (readdata() & 0x7F) << 28; 
        } 
      } 
    } 
    return paramBoolean ? i : (-(i & 0x1) ^ i >>> 1);
  }
  
  public String readString() {
    char[] arrayOfChar;
    int k;
    int j;
    int i = readInt(true);
    switch (i) {
      default:
        k = i - 1;
        if (this.chars.length < k)
          this.chars = new char[k]; 
        arrayOfChar = this.chars;
        i = 0;
        j = 0;
        while (true) {
          if (j < k) {
            i = readdata();
            if (i <= 127) {
              arrayOfChar[j] = (char)i;
              j++;
              continue;
            } 
          } 
          if (j < k)
            readUtf8_slow(k, j, i); 
          return new String(arrayOfChar, 0, k);
        } 
      case 0:
        return null;
      case 1:
        break;
    } 
    return "";
  }
  
  public int readdata() {
	  try {
		  return this.read();
	  }
	  catch(IOException e) {
		  
	  }
	  return -1;
  }
}
