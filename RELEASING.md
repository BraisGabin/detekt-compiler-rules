# Releaising

1. Check that the changelog is updated.
2. Go to [Branch settings](https://github.com/BraisGabin/detekt-compiler-rules/settings/branches) and disable the branch protection for `main`.
3. Run the workflow [Publish to Maven Central][action] with the desired version.
4. Go to [Branch settings](https://github.com/BraisGabin/detekt-compiler-rules/settings/branches) and enable the branch protection again for `main`.
5. Visit [Sonatype Nexus](https://central.sonatype.com/publishing) and promote the artifact.
6. Visit [Releases](https://github.com/BraisGabin/detekt-compiler-rules/releases) and promote the draft to an actual GitHub Release. 

[action]: https://github.com/BraisGabin/detekt-compiler-rules/actions/workflows/maven-crental-publish.yml
