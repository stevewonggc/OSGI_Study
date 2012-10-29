package org.stevesolution.osgistudy.gettingstart;

/**
 * Public API representing an example OSGi service
 */
public interface ExampleService extends Runnable
{
    @Override
    public void run();
}