public class SquareMatrix<T>
{

   private  double[][]  data  =  null ;
   private  int         n     =  0    ;

   public SquareMatrix( int n )
   {
      this . n     =  n                ;
      this . data  =  new double[n][n] ;
   }

   public double get( int i , int j )
   { return this.data[i][j]; }

   public void set( int i , int j , double s )
   { this.data[i][j] = s; }

   public int determinant()
   { return determinant( this.n ); }

   public int determinant( int n )
   {
       double[][] LU = new double[n][n];

       for( int i=0; i<n; i++ )
       for( int j=0; j<n; j++ )
    	   LU[i][j] = this.data[i][j];

       int[] piv = new int[n];

       for( int i=0; i<n; i++ ) piv[i] = i;

       int       sign   = 1             ;
       double[]  LUrowi	= null          ;
       double[]  LUcolj	= new double[n] ;

       for( int j=0; j<n; j++ )
       {
           for( int i=0; i<n; i++ )  LUcolj[i] = LU[i][j];
           for( int i=0; i<n; i++ )
           {
               LUrowi   = LU[i]         ;
               int kmax = Math.min(i,j) ;
               double s	= 0.0           ;

               for( int k=0; k<kmax; k++ )  s += LUrowi[k]*LUcolj[k];

               LUrowi[j] = LUcolj[i] -= s;
           }

           int p = j;

           for( int i=j+1; i<n; i++ ) if( Math.abs(LUcolj[i])>Math.abs(LUcolj[p]) )  p = i;
           if( p!=j )
           {
               for( int k=0; k<n; k++ )
               {
                   double t = LU[p][k] ;
                   LU[p][k] = LU[j][k] ;
                   LU[j][k] = t        ;
               }

               int k  = piv[p] ;
               piv[p] = piv[j] ;
               piv[j] = k      ;
               sign   = -sign  ;
           }

           if( j<n & LU[j][j] != 0.0 ) for( int i=j+1; i<n; i++ )  LU[i][j] /= LU[j][j];
       }

       int d = sign;

       for( int j=0; j<n; j++ )  d *= LU[j][j];

       return d;
   }

   public void print()
   {
       System.out.println();
	   
       //SPEED HACK
       int i = -1 ;
       int j = -1 ;
	   
       while( ++i<n )
       {
           j=-1;
           while( ++j<n ) System.out.print(" "+((int)(data[i][j])));
           System.out.println();
       }
       System.out.println();
   }

   public int[][] toArray()
   {
       int[][] result = new int[n][n];

       for( int i=0; i<data.length; i++ )
       for( int j=0; j<data.length; j++ )
           result[i][j] = (int)data[i][j];

       return result;
    }

}