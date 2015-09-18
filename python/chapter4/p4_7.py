"""
Build Order:

You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the first
project is dependent on the second project). All of a project's dependencies must be built before the project is.
Find the build order that will allow the projects to be built. If there is no valid build order, return an error.

EXAMPLE:

Input:
    projects:
        a, b, c, d, e, f
    dependencies:
        (d, a), (b, f), (d, b), (a, f), (c, d)
Output:
    f, e, a, b, d, c
"""

def findBuildOrder(projects, dependencies):
    """
    Finds the build order by performing topological sorting of the DAG that represents projects and their dependencies.

    :param projects: The list of projects.
    :param dependencies: The list of dependencies (each dependency is a two-element tuple or list).
    :return: The list of projects in a correct build order.
    """
    if projects is None or dependencies is None:
        return None
    # I would use the sets for storing projects and dependencies here, by Python does not have anything like
    # LinkedHashSet in Java that maintains insertion order of the elements
    remaining_deps = dependencies[:]
    remaining_projects = projects[:]
    build_order = []

    while len(remaining_projects) > 0:
        targets = {d[0] for d in remaining_deps}
        origins = [p for p in remaining_projects if p not in targets]
        if len(origins) == 0:
            # there is a cycle in the dependency graph, exiting
            return
        remaining_deps = [dep for dep in remaining_deps if dep[1] not in origins]
        remaining_projects = [p for p in remaining_projects if p not in origins]
        build_order.extend(origins)
    return build_order

if __name__ == '__main__':
    projects = ['a', 'b', 'c', 'd', 'e', 'f']
    dependencies = [('d', 'a'), ('b', 'f'), ('d', 'b'), ('a', 'f'), ('c', 'd')]
    print(findBuildOrder(projects, dependencies))






