package org.embulk.standards;

import org.embulk.config.ConfigSource;
import org.embulk.config.Task;
import org.embulk.config.TaskSource;
import org.embulk.spi.Reporter;
import org.embulk.spi.ReporterPlugin;
import org.embulk.spi.util.Reporters;

import javax.annotation.concurrent.ThreadSafe;

public class StdoutReporterPlugin
        implements ReporterPlugin
{
    @Override
    public TaskSource configureTaskSource(ConfigSource config)
    {
        return config.loadConfig(Task.class).dump();
    }

    @Override
    public Reporter open(TaskSource errorDataTask)
    {
        return new StdoutReporter();
    }

    @ThreadSafe
    private static class StdoutReporter
            implements Reporter
    {
        @Override
        public void skip(String skipped)
        {
            System.out.println("StdoutReporter#skipped");
            System.out.println(skipped);
        }

        @Override
        public void log(Reporters.ReportLevel level, String eventLog)
        {
            System.out.println("StdoutReporter#log");
            System.out.println(eventLog);
        }

        @Override
        public void close()
        {
            System.out.println("StdoutReporter#close");
        }

        @Override
        public void cleanup()
        {
            System.out.println("StdoutReporter#cleanup");
        }
    }
}