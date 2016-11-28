beans-xml-builder
=================

A fluent builder API for the CDI beans descriptor.

Using this API, you can programmatically create a `beans.xml` descriptor for
CDI, or load and edit the contents of an existing descriptor. This is helpful
in various testing scenarios, where the CDI mechanisms of alternative bean 
classes and alternative stereotypes may prove insufficient to describe the
desired bean deployments.

## Example Usage

Use one of the static `DescriptorBuilderFactory` methods to create a
builder instance.

```
DescriptorBuilder builder = DescriptorBuilderFactory.newBuilder();
```

If you wish to edit the contents of an existing descriptor, use one of the
methods that allows the XML source for the descriptor to be specified.

```
DescriptorBuilder builder = DescriptorBuilderFactory.newBuilder(
    new FileInputStream("beans.xml"));
```

Use the methods of the builder to append, insert, or remove items from the
descriptor.

```
builder
    .alternatives()
        .stereotypes()
            .append(Staging.class)
            .end()
    .interceptors()
        .remove(AuthorizationInterceptor.class)
        .insertBefore(LoggingInterceptor.class, MockAuthorizationInterceptor.class)
        .end()
    .buildToStream(new FileOutputStream("test-beans.xml");  
```

The builder provides methods to manipulate alternatives, decorators, and
interceptors, as well as utility methods such as those used to specify the
discovery mode and descriptor version.
