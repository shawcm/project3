import	java.io.IOException;
import	org.apache.hadoop.io.IntWritable;
import	org.apache.hadoop.io.FloatWritable;
import	org.apache.hadoop.io.LongWritable;
import	org.apache.hadoop.io.Text;
import	org.apache.hadoop.mapreduce.Mapper;


public	class	TemperatureMapper
				extends	Mapper<LongWritable,	Text,	Text,	FloatWritable>	{

		@Override
		public	void	map(LongWritable	key,	Text	value,	Context	context)
						throws	IOException,	InterruptedException {
			System.out.println("value: " + value);
			String	line	=	value.toString();

			float	airTemperature;
			airTemperature	=	Float.parseFloat(line.split(",")[2]);
			context.write(new	Text("min"),	new	FloatWritable(airTemperature));
			context.write(new	Text("max"),	new	FloatWritable(airTemperature));
		}
}
