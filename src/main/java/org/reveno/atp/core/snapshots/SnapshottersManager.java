/** 
 *  Copyright (c) 2015 The original author or authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.reveno.atp.core.snapshots;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.reveno.atp.api.RepositorySnapshotter;
import org.reveno.atp.core.api.serialization.RepositoryDataSerializer;
import org.reveno.atp.core.api.storage.SnapshotStorage;

public class SnapshottersManager {

	public RepositorySnapshotter defaultSnapshotter() {
		return snapshotters.get(DefaultSnapshotter.TYPE);
	}
	
	public void registerSnapshotter(RepositorySnapshotter snapshotter) {
		snapshotters.put(snapshotter.getType(), snapshotter);
	}
	
	public void resetSnapshotters() {
		snapshotters = new LinkedHashMap<Long, RepositorySnapshotter>();
	}
	
	public void getSnapshotter(long type) {
		snapshotters.get(type);
	}
	
	public Collection<RepositorySnapshotter> getAll() {
		return snapshotters.values();
	}
	
	
	public SnapshottersManager(SnapshotStorage storage, RepositoryDataSerializer repoSerializer) {
		snapshotters.put(DefaultSnapshotter.TYPE, new DefaultSnapshotter(storage, repoSerializer));
	}
	
	
	protected volatile Map<Long, RepositorySnapshotter> snapshotters = new LinkedHashMap<>();
	
}