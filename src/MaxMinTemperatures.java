import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class MaxMinTemperatures {
	public static void main(String[] args) throws Exception	{

		if (args.length != 2) {
			System.error.Println("usage: MaxMinTemperature <input path> <output path>");
			System.exit(-1);
		}

		// Job Related Configurations
		Configuration conf= new Configuration();
		Job job= new Job(conf, "Max Min Temperatures");
		job.setJarByClass(MaxMinTemperatures.class);

		// Provide path to pickup input file for the job
		FileInputFormat.addInputPath(job, new Path(args[0]));

		// Provide paths to pick the output file for the job, and delete if it already exists
		Path outputPath= new Path(args[1]);
		FileOutputFormat.setOutputPath(job, outputPath);
		outputPath.getFileSystem(conf).delete(outputPath, true);

		// To set the mapper and reducer and combiner of this job
		job.setMapperClass(TemperatureMapper.class);
		job.setReducerClass(TemperatureReducer.class);
		job.setCombinerClass(TemperatureReducer.class);
		
		// Set the input and output format of the class
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		//set up the output key and value classes
		job.setOutputKeyClass(Text.class);	
		job.setOutputValueClass(FloatWritable.class);

		// execute the job
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
