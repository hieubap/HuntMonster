package recycle;

import java.io.IOException;
import java.io.InputStream;

public class Decoder {
  static final int kBitModelTotal = 2048;
  
  static final int kNumBitModelTotalBits = 11;
  
  static final int kNumMoveBits = 5;
  
  static final int kTopMask = -16777216;
  
  int Code;
  
  int Range;
  
  InputStream Stream;
  
  public static void InitBitModels(short[] paramArrayOfshort) {
    for (int i = 0; i < paramArrayOfshort.length; i++)
      paramArrayOfshort[i] = 1024; 
  }
  
  public int DecodeBit(short[] paramArrayOfshort, int paramInt) {
    short s = paramArrayOfshort[paramInt];
    int i = (this.Range >>> 11) * s;
    if ((this.Code ^ Integer.MIN_VALUE) < (Integer.MIN_VALUE ^ i)) {
      this.Range = i;
      paramArrayOfshort[paramInt] = (short)(s + (2048 - s >>> 5));
      if ((this.Range & 0xFF000000) == 0) {
    	 try {
        this.Code = this.Code << 8 | this.Stream.read();
    	 }
    	 catch(IOException e) {
    		 
    	 }
        this.Range <<= 8;
      } 
      return 0;
    } 
    this.Range -= i;
    this.Code -= i;
    paramArrayOfshort[paramInt] = (short)(s - (s >>> 5));
    if ((this.Range & 0xFF000000) == 0) {
      try {
    	this.Code = this.Code << 8 | this.Stream.read();
      }
      catch(IOException e) {
    	  
      }
      this.Range <<= 8;
    } 
    return 1;
  }
  
  public final int DecodeDirectBits(int paramInt) {
    int i = 0;
    while (paramInt != 0) {
      this.Range >>>= 1;
      int j = this.Code - this.Range >>> 31;
      this.Code -= this.Range & j - 1;
      i = i << 1 | 1 - j;
      if ((this.Range & 0xFF000000) == 0) {
        try {
    	  this.Code = this.Code << 8 | this.Stream.read();
        }catch(IOException e) {
        	
        }
        this.Range <<= 8;
      } 
      paramInt--;
    } 
    return i;
  }
  
  public final void Init() {
    int i = 0;
    this.Code = 0;
    this.Range = -1;
    while (i < 5) {
    	try {
      this.Code = this.Code << 8 | this.Stream.read();
    	}catch(IOException e) {
    		
    	}
      i++;
    } 
  }
  
  public final void ReleaseStream() { this.Stream = null; }
  
  public final void SetStream(InputStream paramInputStream) { this.Stream = paramInputStream; }
}
