package com.katspow.caatjagwtdemos.client.welcome.hypernumber.effects;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaCanvasPixelArray;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.core.canvas.CaatjaImageData;

public class Plasma {
    
    CaatjaImageData dstImageData= null;

    double m_awidth=   0;
    double m_aheight=  0;

    // plasma
    int[]wavetable=  null;
    int[] m_colorMap= null;
    int spd1=       1;
    int spd2=       2;
    int spd3=       3;
    int spd4=       4;
    int pos1=       0;
    int pos2=       0;
    int pos3=       0;
    int pos4=       0;
    int tpos1=      0;
    int tpos2=      0;
    int tpos3=      0;
    int tpos4=      0;
    int m_colorMapSize= 256;
    int i1=             0;
    int i2=             0;
    int i3=             0;
    int i4=             0;
    boolean b1=             false;
    boolean b2=             false;
    boolean b3=             false;
    boolean b4=             false;
    int fc=             1000;
    int mfc=            1000;

    int[] color= new int[]{ 0xff00ff, 0xffff00, 0xff0000, 0x000000};

    int timeStep=       20;
    CaatjaCanvas canvas=         null;
    CaatjaContext2d ctx=            null;

    double prevTime =       0;
    
    // Add by me
    private double centerX;
    private double centerY;

    public void setB()  {
        this.b1= (Math.random()>.5);
        this.b2= (Math.random()>.5);
        this.b3= (Math.random()>.5);
        this.b4= (Math.random()>.5);

        this.spd1= (int) Math.floor(((Math.random()*3+1)*((Math.random()<.5) ? 1 : -1)));
        this.spd2= (int) Math.floor(((Math.random()*3+1)*((Math.random()<.5) ? 1 : -1)));
        this.spd3= (int) Math.floor(((Math.random()*3+1)*((Math.random()<.5) ? 1 : -1)));
        this.spd4= (int) Math.floor(((Math.random()*3+1)*((Math.random()<.5) ? 1 : -1)));

        this.i1= (int) Math.floor(((Math.random()*2.4+1)*((Math.random()<.5) ? 1 : -1)));
        this.i2= (int) Math.floor(((Math.random()*2.4+1)*((Math.random()<.5) ? 1 : -1)));
        this.i3= (int) Math.floor(((Math.random()*2.4+1)*((Math.random()<.5) ? 1 : -1)));
        this.i4= (int) Math.floor(((Math.random()*2.4+1)*((Math.random()<.5) ? 1 : -1)));
        
        this.spd1>>=0;
        this.spd2>>=0;
        this.spd3>>=0;
        this.spd4>>=0;

        this.i1>>=0;
        this.i2>>=0;
        this.i3>>=0;
        this.i4>>=0;
    }
    public int[] makeArray  (int size, int initvalue) {
        int[] a = new int[size];
        for(int i=0; i<size; i++ )  {
            a[i] = initvalue;
        }
        return a;
    }
    public void setupPlasma  ()   {
        int x, y;
        this.wavetable=     this.makeArray(256,0);
        this.m_colorMap=    this.makeArray(this.m_colorMapSize,0);

        int r= 0xff<<16;
        int g= 0xff<<16;
        int b= 0;
        int c;

        int i;

        for( i=0; i<this.m_colorMapSize/3; i++ ) {
            b+= ( (0xff<<16)/(this.m_colorMapSize/3) );
            c= 0xff000000 | ( (r>>16)<<16 ) | ( (g>>16)<<8 ) | (b>>16);
            this.m_colorMap[ i+2*(this.m_colorMapSize/3) ]= c;
        }

        r= 0xff<<16;
        b=0;
        g=0;

        for( i=0; i<this.m_colorMapSize/3; i++ ) {
            g+= ( (0xff<<16)/(this.m_colorMapSize/3) );
            c= 0xff000000 | ( (r>>16)<<16 ) | ( (g>>16)<<8 ) | (b>>16);
            this.m_colorMap[ (this.m_colorMapSize/3)+i ]= c;
        }

        g=0;
        b=0;
        r=0;

        for( i=0; i<this.m_colorMapSize/3; i++ ) {
            r+= ( (0xff<<16)/(this.m_colorMapSize/3) );
            c= 0xff000000 | ( (r>>16)<<16 ) | ( (g>>16)<<8 ) | (b>>16);
            this.m_colorMap[ i ]= c;
        }

        for (x=0; x<256; x++)   {
            this.wavetable[x] = (int) Math.floor(32 * (1 + Math.cos(x*2 * Math.PI / 256)));
        }

        this.pos1=(int) Math.floor(255*Math.random());
        this.pos2=(int)Math.floor(255*Math.random());
        this.pos3=(int)Math.floor(255*Math.random());
        this.pos4=(int)Math.floor(255*Math.random());
    }
    public void plasmaStep  (CaatjaCanvasPixelArray dstPixels ) {
        int v = 0;
        this.tpos1 = this.pos1;
        this.tpos2 = this.pos2;

        for (int x=0; x<this.m_aheight; x++) {
            this.tpos3 = this.pos3;
            this.tpos4 = this.pos4;
            for(int y=0; y<this.m_awidth; y++) {
/*
            var o1= ( b1 ) ? tpos1+tpos2+tpos3 : tpos1-tpos2;
            var o2= ( b2 ) ? tpos2+tpos3 : tpos2-tpos3;
            var o3= ( b3 ) ? tpos3+tpos4 : tpos3-tpos4;
            var o4= ( b4 ) ? tpos4+tpos1-tpos2 : tpos4-tpos1;
*/

            int o1= this.tpos1+this.tpos2+this.tpos3;
            int o2= this.tpos2+this.tpos3-this.tpos1;
            int o3= this.tpos3+this.tpos4-this.tpos2;
            int o4= this.tpos4+this.tpos1-this.tpos2;

            if ( this.b1 ) o1= -o1;
            if ( this.b2 ) o2= -o2;
            if ( this.b3 ) o3= -o3;
            if ( this.b4 ) o4= -o4;

            int z = (int) Math.floor(
                this.wavetable[o1&255] +
                this.wavetable[o2&255] +
                this.wavetable[o3&255] +
                this.wavetable[o4&255] );

            this.setPixel(v,dstPixels,this.m_colorMap[z]);

            this.tpos3 += this.i1;
            this.tpos3&=255;
            this.tpos4 += this.i2;
            this.tpos4&=255;
            v++;
            }

            this.tpos1 += this.i3;
            this.tpos1&=255;
            this.tpos2 += this.i4;
            this.tpos2&=255;
        }

        this.pos1 += this.spd1;
        this.pos2 -= this.spd2;
        this.pos3 += this.spd3;
        this.pos4 -= this.spd4;
        this.pos1&=255;
        this.pos3&=255;
        this.pos2&=255;
        this.pos4&=255;
    }
    public void setupColor  () {
        int[] pal= this.makeArray(256*3,0);
        int pos=0;
        int chunk= 256/(this.color.length-1);
        int i;
        int j;

        for( i=0; i<this.color.length-1; i++ ) {
            int c= this.color[i];
            int r0= (c&0xff0000)>>16;
        int g0= (c&0xff00)>>8;
        int b0= c&0xff;

        int c1= this.color[i+1];
            int r1= (c1&0xff0000)>>16;
            int g1= (c1&0xff00)>>8;
            int b1= c1&0xff;

            double dr= Math.floor(((r1-r0)<<16)/chunk);
            double dg= Math.floor(((g1-g0)<<16)/chunk);
            double db= Math.floor(((b1-b0)<<16)/chunk);

            r0<<=16;
            g0<<=16;
            b0<<=16;

            for( j=0; j<chunk; j++ ) {
                pal[ pos*3   ]= r0>>16;
                pal[ pos*3+1 ]= g0>>16;
                pal[ pos*3+2 ]= b0>>16;
                pos++;
                r0+=dr;
                g0+=dg;
                b0+=db;
            }

        }

        for( i=0; i<256; i++ ) {
            this.m_colorMap[ i ]= pal[ i*3 ]<<16 | pal[ i*3+1 ]<<8 | pal[ i*3+2 ] | 0xff000000;
        }
    }
    public void init  () {
        this.setupPlasma();
        this.setupColor();
    }
    public Plasma initialize  (double width, double height){

        this.canvas = Caatja.createCanvas();
        
        this.ctx= this.canvas.getContext2d();

        this.m_awidth= width;
        this.m_aheight= height;

        this.centerX= this.m_awidth/2;
        this.centerY= this.m_aheight/2;
        
        this.canvas.setCoordinateSpaceWidth((int) this.m_awidth);
        this.canvas.setCoordinateSpaceHeight((int) this.m_aheight);

        this.dstImageData= this.ctx.getImageData(0,0,width,height);
        
        
        Caatja.addCanvas(canvas);
        
        this.init();

        return this;
    }
    
    public int pixel(int pos, int[]arr )  {
        pos*=4;
        return arr[pos]<<16 | arr[pos+1]<<8 | arr[pos+2];
    }
    
    public void setPixel(int pos, CaatjaCanvasPixelArray arr, int argb ) {
        pos*=4;
        arr.set(pos, (argb & 0xff0000) >> 16);
        arr.set(pos + 1, (argb & 0xff00) >> 8);
        arr.set(pos + 2, argb & 0xff);
        arr.set(pos + 3, 255);
    }
    
    public void plasmaLoop(double time){

        if ( this.mfc!=-1 ) {
            if ( this.fc>this.mfc ) {
                this.setB( );
                this.fc=0;
            }
        }

        if ( time-this.prevTime>0 ) {
            this.plasmaStep( this.dstImageData.getData() );
            this.prevTime= time;
        }
        this.ctx.putImageData( this.dstImageData, 0, 0 );
        this.fc++;

    }

}
