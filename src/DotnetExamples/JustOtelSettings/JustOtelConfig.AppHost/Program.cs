using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

var builder = DistributedApplication.CreateBuilder(args);

// Add the ElasticApmConfiguration to the DI container
ElasticApmConfiguration elasticApmConfiguration = new();
builder.Configuration.GetSection("ElasticApm").Bind(elasticApmConfiguration);




var apiService = builder.AddProject<Projects.JustOtelConfig_ApiService>("apiservice")/
    .WithElasticApm(elasticApmConfiguration);

builder.AddProject<Projects.JustOtelConfig_Web>("webfrontend")
    .WithExternalHttpEndpoints()
    .WithEnvironment("ASPNETCORE_ENVIRONMENT", "Development")
    .WithReference(apiService)
    .WithElasticApm(elasticApmConfiguration);

builder.Build().Run();



// This is here to make show the extension method along with the usage of it. As the number of projects and number of extensions grow, it will be hard to keep track of all the extension methods so you will want to seperate them into their own files later on.
public static class ElasticApmExtensions
{
    public static IResourceBuilder<T> WithElasticApm<T>(this IResourceBuilder<T> builder, ElasticApmConfiguration elasticApmConfiguration) where T : IResourceWithEnvironment
    {
        string endpoint = elasticApmConfiguration.Endpoint;
        string bearerToken = elasticApmConfiguration.BearerToken;

        builder.WithEnvironment("OTEL_EXPORTER_OTLP_ENDPOINT", endpoint);

        builder.WithEnvironment("OTEL_EXPORTER_OTLP_HEADERS", $"Authorization = Bearer {bearerToken}");

        builder.WithEnvironment("OTEL_EXPORTER_OTLP_PROTOCOL", "http");

        return builder;
    }
}



public class ElasticApmConfiguration
{
    public string Endpoint { get; set; } = null!;
    public string BearerToken { get; set; } = null!;
}