import	java.io.IOException;
import	org.apache.hadoop.io.IntWritable;
import	org.apache.hadoop.io.FloatWritable;
import	org.apache.hadoop.io.Text;
import	org.apache.hadoop.mapreduce.Reducer;


public	class	TemperatureReducer
				extends	Reducer<Text,	FloatWritable,	Text,	FloatWritable>	{
		
		@Override
		public	void	reduce(Text	key,	Iterable<FloatWritable>	values,	Context	context)
						throws	IOException,	InterruptedException	{
				
				float val = key.equals(Text("min")) ? Float.MAX_VALUE : Float.MIN_VALUE;

				for	(FloatWritable	value	:	values)	{
						if (key.equals(Text("max"))) {
							val	=	Math.max(val,	value.get());
						} else {
							val	=	Math.min(val,	value.get());
						}
				}
				context.write(key,	new	FloatWritable(val));
		}
}